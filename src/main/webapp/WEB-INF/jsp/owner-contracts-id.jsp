<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<%@ include file="/WEB-INF/jspf/owner-profile-nav.jspf" %>

    <div class="container">
        <div class="row gutters d-flex justify-content-center">
            <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12 mt-3">
                <div class="card">
                    <div class="card-header">
                        <h6 class="mb-2 text-primary">Contract</h6>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
                                <div class="form-col">
                                    <span><b>Owner details:</b><br>Name Surname</span><br>
                                    <span>000-000-000</span><br>
                                    <span>example@gmail.com</span>
                                </div>
                            </div>
                            <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
                                <div class="form-col">
                                    <span><b>Host details:</b><br>Name Surname</span><br>
                                    <span>000-000-000</span><br>
                                    <span>example@gmail.com</span>
                                </div>
                            </div>
                            <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
                                <div class="form-col">
                                    <span><b>Contract details:</b></span><br>
                                    <span>Start date: 05/20/21</span><br>
                                    <span>End date: 05/23/21</span>
                                </div>
                            </div>
                        </div>
                        <div class="row mt-4">
                            <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
                                <div class="form-col">
                                    <p><b>Pet:</b> dog</p>
                                </div>
                            </div>
                            <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
                                <div class="form-col">
                                    <!-- empty -->
                                </div>
                            </div>
                            <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
                                <div class="form-col">
                                    <p><b>Status:</b>
                                        <span class="badge bg-info text-wrap" style="width: 5rem;">new</span>
                                        <span class="badge bg-warning text-wrap" style="width: 5rem;">accepted</span>
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                <div class="text-right">
                                    <a href="#"><button type="button" id="submit1" name="submit" class="btn btn-danger mx-1 mt-1">Cancel</button></a>
                                    <a href="#"><button type="button" id="submit2" name="submit" class="btn btn-outline-danger mx-1 mt-1">Error</button></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card">
                    <div class="card-header">
                        <h6 class="mb-2 text-primary">Contract</h6>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
                                <div class="form-col">
                                    <span><b>Owner details:</b><br>Name Surname</span><br>
                                    <span>000-000-000</span><br>
                                    <span>example@gmail.com</span>
                                </div>
                            </div>
                            <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
                                <div class="form-col">
                                    <span><b>Host details:</b><br>Name Surname</span><br>
                                    <span>000-000-000</span><br>
                                    <span>example@gmail.com</span>
                                </div>
                            </div>
                            <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
                                <div class="form-col">
                                    <span><b>Contract details:</b></span><br>
                                    <span>Start date: 05/20/21</span><br>
                                    <span>End date: 05/23/21</span>
                                </div>
                            </div>
                        </div>
                        <div class="row mt-4">
                            <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
                                <div class="form-col">
                                    <p><b>Pet:</b> dog</p>
                                </div>
                            </div>
                            <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
                                <div class="form-col">
                                    <!-- empty -->
                                </div>
                            </div>
                            <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
                                <div class="form-col">
                                    <p><b>Status:</b>
                                        <span class="badge bg-success text-wrap" style="width: 5rem;">in process</span>
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                <div class="text-right">
                                    <a href="#"><button type="button" id="submit3" name="submit" class="btn btn-outline-danger mx-1 mt-1">Error</button></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card">
                    <div class="card-header">
                        <h6 class="mb-2 text-primary">Contract</h6>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
                                <div class="form-col">
                                    <span><b>Owner details:</b><br>Name Surname</span><br>
                                    <span>000-000-000</span><br>
                                    <span>example@gmail.com</span>
                                </div>
                            </div>
                            <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
                                <div class="form-col">
                                    <span><b>Host details:</b><br>Name Surname</span><br>
                                    <span>000-000-000</span><br>
                                    <span>example@gmail.com</span>
                                </div>
                            </div>
                            <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
                                <div class="form-col">
                                    <span><b>Contract details:</b></span><br>
                                    <span>Start date: 05/20/21</span><br>
                                    <span>End date: 05/23/21</span>
                                </div>
                            </div>
                        </div>
                        <div class="row mt-4">
                            <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
                                <div class="form-col">
                                    <p><b>Pet:</b> dog</p>
                                </div>
                            </div>
                            <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
                                <div class="form-col">
                                    <div class="form-group">
                                        <label for="rating"><b>Rated: 3,3/-</b></label>
                                        <div id="rating" class="form-control-static">
                                            <i class="text-warning fa fa-star"></i>
                                            <i class="text-warning fa fa-star"></i>
                                            <i class="text-warning fa fa-star"></i>
                                            <i class="text-secondary fa fa-star"></i>
                                            <i class="text-secondary fa fa-star"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
                                <div class="form-col">
                                    <p><b>Status:</b>
                                        <span class="badge bg-secondary text-wrap" style="width: 5rem;">ended</span>
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                <div class="text-right">
                                    <a href="#"><button type="button" id="submit" name="submit" class="btn btn-outline-danger mx-1 mt-1">Error</button></a>
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