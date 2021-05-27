<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
    <!-- datepicker -->
    <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
    <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />
    
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
        <a href="#" class="navbar-brand">Take<b>My</b>Pet</a>
        <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div id="navbarCollapse" class="collapse navbar-collapse justify-content-start">
            <div class="navbar-nav">
                <a href="#" class="nav-item nav-link">Profile</a>
                <a href="#" class="nav-item nav-link">Posts</a>
                <a href="#" class="nav-item nav-link">Contracts</a>
            </div>
        </div>
    </nav>

    <div class="container">
        <div class="row gutters d-flex justify-content-center">
            <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12 mt-3">
                <div class="card">
                    <div class="card-header">
                        <h6 class="mb-2 text-primary">Contract</h6>
                    </div>
                    <div class="card-body">
                        <div class="row gutters">
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-col">
                                    <p><b>Host:</b> Name Surname</p>
                                    <p><b>Pet:</b><span> dog</span></p>
                                    <p><b>Max term:</b><span> 10 days</span></p>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-col">
                                    <p><b>Address:</b> Kyiv</p>
                                    <p><b>Phone:</b><span> 000-000-000</span></p>
                                    <div class="form-group">
                                        <label for="rating"><b>Rating: 3,3</b></label>
                                        <div class="form-control-static" id="rating">
                                            <i class="text-warning fa fa-star"></i>
                                            <i class="text-warning fa fa-star"></i>
                                            <i class="text-warning fa fa-star"></i>
                                            <i class="text-secondary fa fa-star"></i>
                                            <i class="text-secondary fa fa-star"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-col">
                                    Start Date: <input id="startDate" width="276" />
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-col align-right">
                                    End Date: <input id="endDate" width="276" />
                                </div>
                            </div>
                            <script>
                                var today = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate());
                                $('#startDate').datepicker({
                                    uiLibrary: 'bootstrap4',
                                    iconsLibrary: 'fontawesome',
                                    minDate: today,
                                    maxDate: function () {
                                        return $('#endDate').val();
                                    }
                                });
                                $('#endDate').datepicker({
                                    uiLibrary: 'bootstrap4',
                                    iconsLibrary: 'fontawesome',
                                    minDate: function () {
                                        return $('#startDate').val();
                                    }
                                });
                            </script>
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                <div class="my-2 text-right">
                                    <a href="#"><button type="button" id="submit" name="submit" class="btn btn-primary mx-1 mt-1">Submit</button></a>
                                </div>
                            </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>

</body>
</html>