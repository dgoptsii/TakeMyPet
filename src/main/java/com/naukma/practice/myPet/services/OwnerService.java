package com.naukma.practice.myPet.services;

import com.naukma.practice.myPet.db.*;
import com.naukma.practice.myPet.db.DTO.OwnerDTO;
import com.naukma.practice.myPet.db.entity.*;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * Service class with methods to work with owner's entities
 */

@Service
@Slf4j
public class OwnerService implements OwnerServiceInterface{

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ContractRepository contractRepository;

    public void createOwnerContractPage(int page, int size, String status, Model model, String login) throws Exception {
        try {
            List<Contract> contracts;

            Pageable paging = PageRequest.of(page, size);

            Page<Contract> pageContracts;
            pageContracts = getContracts(status, login, paging);

            contracts = pageContracts.getContent();
            if (contracts.size() == 0) {
                model.addAttribute("message", "Oops. No contracts...");
            } else {
                contracts = pageContracts.getContent();
            }
            model.addAttribute("contractsList", contracts);
            model.addAttribute("currentPage", page + 1);
            model.addAttribute("totalItems", pageContracts.getTotalElements());
            model.addAttribute("totalPages", pageContracts.getTotalPages());
            model.addAttribute("status", status);

        } catch (Exception e) {
            throw new Exception("ERROR");
        }
    }

    public Page<Contract> getContracts(String status, String login, Pageable paging) {
        Page<Contract> pageContracts;
        if (status.equals("ALL")) {
            pageContracts
                    = contractRepository.findAllByOwnerLoginOrderByStartDateDesc(login, paging);
        } else {
            pageContracts
                    = contractRepository.findAllByOwnerLoginAndStatusOrderByStartDateDesc(login, status.toUpperCase(), paging);
        }
        return pageContracts;
    }

    public void createContract(String startDate, String endDate, Long id,
                                HttpServletRequest request, HttpServletResponse response)
            throws NotFoundException, IOException {
        Post post;
        if (postRepository.findById(id).isPresent()) {
            post = postRepository.findById(id).get();
        } else {
            //TODO add custom exception
            throw new NotFoundException("Error. Contract for not existing post! ");
        }

        Date start = Date.valueOf(dateToFormat(startDate));
        Date end = Date.valueOf(dateToFormat(endDate));
        Host host = post.getHost();

        List<Contract> list = contractRepository.findAllDistinctByHostAndEndDateAfterOrStartDateBefore(host, start, end);
        int days = countDays(start, end);

        if (start.before(end) && days <= post.getMaxDays() && countInter(list, start, end, host.getMaxAnimals())) {
            String login = (String) request.getSession().getAttribute("userLogin");
            Owner owner = ownerRepository.findOwnerByLogin(login).get();
            Contract contract = Contract.createContract(post, owner, start, end, days);
            Contract result = contractRepository.save(contract);

            request.getSession().setAttribute("getAlert", "success");
            response.sendRedirect(request.getContextPath() + "/owner/posts");

        } else {
            //TODO add custom exception (errorSchedule)
            request.getSession().setAttribute("getAlert", "error");
            request.getSession().setAttribute("errorMessage", "Maximum number of animals for this host. Please, select other dates.");
            response.sendRedirect(request.getContextPath() + "/owner/createContract/" + id + "?startDate=" + startDate + "&endDate=" + endDate);
        }
    }

    public int countDays(Date start, Date end) {
        long diffInMillies = Math.abs(start.getTime() - end.getTime());
        int diff = (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return diff;
    }

    public void createContractPage(@PathVariable Long id, Model model) throws NotFoundException {
        Post post;
        if (postRepository.findById(id).isPresent()) {
            post = postRepository.findById(id).get();
        } else {
            //TODO add custom exception
            throw new NotFoundException("Error. Contract for not existing post! ");
        }
        model.addAttribute("postInfo", post);
    }

    public String dateToFormat(String date) {

        //05/31/2021
        //2019-01-26
        Pattern pattern = Pattern.compile("\\d{2,}");
        Matcher matcher = pattern.matcher(date);

        matcher.find();
        String day = matcher.group();
        matcher.find();
        String month = matcher.group();
        matcher.find();
        String year = matcher.group();

        return new StringBuilder(year).append('-').append(day).append('-').append(month).toString();
    }

    public boolean countInter(List<Contract> list, Date start, Date end, int maxInter) {
        int i = 0;
        for (Contract c : list) {
            if ((c.getStartDate().compareTo(start) <= 0 && c.getEndDate().compareTo(start) >= 0) ||
                    (c.getStartDate().compareTo(end) <= 0 && c.getEndDate().compareTo(end) >= 0) ||
                    (c.getStartDate().compareTo(start) >= 0 && c.getStartDate().compareTo(end) <= 0)) i++;

            if (i > maxInter - 1) {
                return false;
            }

        }
        return true;
    }

    public void createPostPage(@PathVariable Long id, Model model) throws NotFoundException {
        Post post;
        if (postRepository.findById(id).isPresent()) {
            post = postRepository.findById(id).get();
        } else {
            //TODO add custom exception
            throw new NotFoundException("Post with this id doesn't exist");
        }
        model.addAttribute("postInfo", post);
    }

    /**
     * Finds and creates DTO entity of owner from database
     * @param login of owner
     * @return DTO entity of owner
     */
    public OwnerDTO findOwnerDto(String login) {
        Owner owner = ownerRepository.findOwnerByLogin(login).get();
        User user = userRepository.findUserByLogin(login).get();
        return OwnerDTO.createOwner(owner, user);
    }

    public void editOwnerProfile(OwnerDTO ownerNew, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = (String) request.getSession().getAttribute("userLogin");

        Owner owner = ownerRepository.findOwnerByLogin(login).get();
        User user = userRepository.findUserByLogin(login).get();

        OwnerDTO currentInfo = OwnerDTO.createOwner(owner, user);

        if (currentInfo.equals(ownerNew)) {
            request.getSession().setAttribute("getAlert", "error");
            request.getSession().setAttribute("errorMessage", "You didn't change anything!");
            response.sendRedirect(request.getContextPath() + "/owner/profile/edit");
        } else if (
                (userRepository.findUserByLogin(ownerNew.getLogin()).isPresent() && !currentInfo.getLogin().equals(ownerNew.getLogin()))
                        || (userRepository.findUserByEmail(ownerNew.getEmail()).isPresent() && !currentInfo.getEmail().equals(ownerNew.getEmail()))
        ) {
            request.getSession().setAttribute("getAlert", "error");
            request.getSession().setAttribute("errorMessage", "User with this login/e-mail is already exist!");
            response.sendRedirect(request.getContextPath() + "/owner/profile/edit");
        } else if (ownerRepository.findOwnerByPhone(ownerNew.getPhone()).isPresent() && !currentInfo.getPhone().equals(ownerNew.getPhone())) {
            request.getSession().setAttribute("getAlert", "error");
            request.getSession().setAttribute("errorMessage", "User with this phone number is already exist!");
            response.sendRedirect(request.getContextPath() + "/owner/profile/edit");

        } else {
            user.setLogin(ownerNew.getLogin());
            user.setEmail(ownerNew.getEmail());

            System.out.println("Updated user -> " + userRepository.save(user));
            owner = OwnerDTO.createOwnerFromDTO(ownerNew);
            System.out.println("Updated owner -> " + ownerRepository.save(owner));
            request.getSession().setAttribute("userLogin", ownerNew.getLogin());

            request.getSession().setAttribute("getAlert", "success");
            response.sendRedirect(request.getContextPath() + "/owner/profile");
        }
    }


    public void createPostsPageOwner(int page, int size, String animal, Model model, HttpServletRequest request, long animalId, int maxDays) throws Exception {
        String login = (String) request.getSession().getAttribute("userLogin");
        Owner owner = ownerRepository.findOwnerByLogin(login).get();

        String region = owner.getRegion();

        try {
            List<Post> posts;
            Pageable paging = PageRequest.of(page, size);

            Page<Post> pagePosts = null;

            if (animalId != 0 && maxDays != 0) {
                pagePosts = postRepository.findDistinctByAnimalIdAndMaxDaysGreaterThanEqualAndStatus(animalId, maxDays, "ACTIVE", paging);
            } else if (animalId != 0) {
                pagePosts = postRepository.findDistinctByAnimalIdAndStatus(animalId, "ACTIVE", paging);
            } else if (maxDays != 0) {
                pagePosts = postRepository.findByMaxDaysGreaterThanEqualAndStatus(maxDays, "ACTIVE", paging);
            } else {
                pagePosts = postRepository.findAllByStatus("ACTIVE", paging);
            }

            posts = pagePosts.getContent();
            posts = posts
                    .stream()
                    .filter(p -> p.getHost().getRegion().equals(region))
                    .filter(p -> p.getStatus().equals("ACTIVE"))
                    .collect(Collectors.toList());

            if (posts.size() == 0) {
                model.addAttribute("message", "Oops. No results founded ...");
            } else {
                model.addAttribute("posts", posts);
            }

            model.addAttribute("currentPage", page + 1);
            model.addAttribute("animals", animalRepository.findAll());
            model.addAttribute("animal", animal);
            model.addAttribute("maxDays", maxDays);
            model.addAttribute("totalItems", pagePosts.getTotalElements());
            model.addAttribute("totalPages", pagePosts.getTotalPages());
        } catch (Exception e) {
            throw new Exception("ERROR");
        }
    }

}
