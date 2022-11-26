<%--
  Created by IntelliJ IDEA.
  User: daria
  Date: 5/29/2021
  Time: 4:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">


<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
    <script type="text/javascript">

        $(document).ready(function(){
            $("#menu").on("click","a", function (event) {
                //отменяем стандартную обработку нажатия по ссылке
                event.preventDefault();

                //забираем идентификатор бока с атрибута href
                var id  = $(this).attr('href'),

                    //узнаем высоту от начала страницы до блока на который ссылается якорь
                    top = $(id).offset().top- $("#menu").height();

                //анимируем переход на расстояние - top за 1500 мс
                $('body,html').animate({scrollTop: top}, 500);
            });
            $("#foot").on("click","a", function (event) {
                //отменяем стандартную обработку нажатия по ссылке
                event.preventDefault();

                //забираем идентификатор бока с атрибута href
                var id  = $(this).attr('href'),

                    //узнаем высоту от начала страницы до блока на который ссылается якорь
                    top = $(id).offset().top;

                //анимируем переход на расстояние - top за 1500 мс
                $('body,html').animate({scrollTop: top}, 500);
            });
        });

    </script>
    <style>
        .carousel-inner img {
            width: 60%;
            margin: auto;
        }

        .carousel-control-prev-icon {
            width: 40px;
            height: 40px;
            background-image: url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' fill='%23292b2c' width='8' height='8' viewBox='0 0 8 8'%3e%3cpath d='M5.25 0l-4 4 4 4 1.5-1.5L4.25 4l2.5-2.5L5.25 0z'/%3e%3c/svg%3e");
        }

        .carousel-control-next-icon {
            width: 40px;
            height: 40px;
            background-image: url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' fill='%23292b2c' width='8' height='8' viewBox='0 0 8 8'%3e%3cpath d='M2.75 0l-1.5 1.5L3.75 4l-2.5 2.5L2.75 8l4-4-4-4z'/%3e%3c/svg%3e");
        }

        .carousel-indicators .active {
            background-color: #292b2c;
        }

p{
    font-size:18px;
}
        body {
            margin: 0;
            padding-top: 0px;
            color: #2e323c;
            position: relative;
            background-color: white !important;
            height: 90%;
        }
    </style>
</head>
<body>
<section id="tag0">
    <%@ include file="/WEB-INF/jspf/welcome-nav.jspf" %>
</section>
<br>
<br><br>
<br>
<br>

<section id="tag1">
    <div id="demo" class="carousel slide text-center" data-ride="carousel">
        <!-- Indicators -->
        <ul class="carousel-indicators">
            <li data-target="#demo" data-slide-to="0" class="active"></li>
            <li data-target="#demo" data-slide-to="1"></li>
            <li data-target="#demo" data-slide-to="2"></li>
            <li data-target="#demo" data-slide-to="3"></li>
            <li data-target="#demo" data-slide-to="4"></li>
        </ul>

        <!-- The slideshow -->
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="https://i.postimg.cc/MGS4H4yS/Screenshot-6.jpg"
                     alt="Slide1">
                <%--                    <div class="carousel-caption">--%>
                <%--&lt;%&ndash;                        <h3>Собака раз</h3>&ndash;%&gt;--%>
                <%--                    </div>--%>
            </div>
            <div class="carousel-item">
                <img src="https://i.postimg.cc/cCky8mdc/Screenshot-1.jpg"
                     alt="Slide2">
            </div>
            <div class="carousel-item">
                <img src="https://i.postimg.cc/0NYTCx8j/Screenshot-2.jpg"
                     alt="Slide3">
            </div>
            <div class="carousel-item">
                <img src="https://i.postimg.cc/mDVK0w1G/Screenshot-3.jpg"
                     alt="Slide4">
            </div>
            <div class="carousel-item">
                <img src="https://i.postimg.cc/WpWQ85PG/Screenshot-4.jpg"
                     alt="Slide5">
            </div>
            <!-- Left and right controls -->
            <a class="carousel-control-prev" href="#demo" data-slide="prev">
                <span class="carousel-control-prev-icon"></span>
            </a>
            <a class="carousel-control-next" href="#demo" data-slide="next">
                <span class="carousel-control-next-icon"></span>
            </a>
        </div>
    </div>
</section>
<!-- Marketing messaging and featurettes
   ================================================== -->
<!-- Wrap the rest of the page in another container to center all the content. -->

<section>
    <div class="container">

        <div  id="tag3" class="form-outline mb-4 pt-5 pb-5 text-center">
            <p class="text-muted d-flex justify-content-center" style="font-size:20px">Ready to become a part of our team?</p>
            <h2 class="d-flex justify-content-center">JOIN US!</h2>

            <div class="row text-center">
                <div class="col" >
                    <p class="text-muted d-flex justify-content-center" >New here?</p>
                    <a href="/registration">   <button  id="register" type="submit" class="btn btn-success" style="width:50%;
                     min-width:100px; min-height:25px; height:70%; "><i class="fa fa-user-plus"></i> Register</button></a>

                </div>
                <div class="col">
                    <p class="text-muted d-flex justify-content-center" >Already have an account?</p>
                  <a href="/login"> <button  id="login" type="submit" class="btn btn-success" style="width:50%;
                     min-width:100px; min-height:25px; height:70%; "><i class="fa fa-user"></i> Login</button></a>
                </div>
            </div>
        </div>

    </div>
</section>
<section class="page-section" id="tag4">
    <div class="container pt-5 mt-5">
        <div class="row justify-content-center">
            <div class="col-lg-8 text-center">
                <h3 class="mt-0">LET`S GET IN TOUCH!</h3>
                <p class="text-muted mb-3" >Is there something you want to tell us? We value each of our customers and
                    will be happy to know
                    your feedback!</p>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-4 ml-auto text-center">
                <i class="fa fa-phone"></i>
                <div><a class="text-success" href="tel:+905556867707">+90 (555) 686 77 07</a></div>
            </div>
            <div class="col-lg-4 mr-auto text-center">
                <i class="fa fa-envelope-open"></i>
                <a class="d-block text-success" href="mailto:feedback@gmail.com">feedback@gmail.com</a>
            </div>
        </div>
    </div>
</section>
<!-- FOOTER -->
<footer class="container py-3 pt-5" id="foot">
    <p class="float-right"><a class="text-success" href="#tag0">Back to top</a></p>
    <p>&copy; 2021 FrogProg Inc.</p>
</footer>
</body>
</html>
