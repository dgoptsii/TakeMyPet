<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
    
</head>
<body>
<%@ include file="/WEB-INF/jspf/host-profile-nav.jspf" %>

    <div class="container">
        <div class="row gutters d-flex justify-content-center">
            <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12 mt-3">
                <div class="row gutters">
                    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 pb-3 mt-3">
                        <div class="text-center">
                            <button type="button" id="submit" name="submit" class="btn btn-primary">+ Add post</button>
                        </div>
                    </div>
                    <div class="text-center col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                        <h6 class="mt-2 mb-2 text-primary">List of posts</h6>
                    </div>
                </div>
                <div class="card">
                    <div class="card-header">
                        <h6 class="mb-2 text-primary">Post</h6>
                    </div>
                    <div class="card-body">
                        <div class="row g-2">
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <span>Pet: cat</span>
                            </div>
                            <div class="text-right col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="btn-group btn-group-toggle" data-toggle="buttons">
                                    <label class="btn btn-outline-success active">
                                      <input type="radio" name="options" id="option1" autocomplete="off" checked>Active
                                    </label>
                                    <label class="btn btn-outline-danger">
                                      <input type="radio" name="options" id="option2" autocomplete="off">Passive
                                    </label>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <span>Term: 5 days</span>
                            </div>
                            <div class="text-right col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <a href="#"><button type="button" id="edit_b" name="submit" class="btn btn-primary mx-1 mt-1">Edit</button></a>
                                <a href="#"><button type="button" id="delete_b" name="submit" class="btn btn-danger mx-1 mt-1">Delete</button></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>