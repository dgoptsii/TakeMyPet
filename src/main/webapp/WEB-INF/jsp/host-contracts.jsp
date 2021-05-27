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
                <div class="text-center col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                    <h6 class="mt-2 mb-2 text-primary">List of contracts</h6>
                </div>
                <div class="card">
                    <div class="card-header">
                        <h6 class="mb-2 text-primary">Contract</h6>
                    </div>
                    <div class="card-body">
                        <div class="row g-2">
                            <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
                                <div class="form-col">
                                    <p><b>Owner:</b> Name Surname</p>
                                    <p><b>Pet:</b><span> dog</span></p>
                                    <p><b>Status:</b>
                                        <span class="badge bg-info text-wrap" style="width: 5rem;">
                                            new
                                        </span>
                                        <span class="badge bg-warning text-wrap" style="width: 5rem;">
                                            accepted
                                        </span>
                                        <span class="badge bg-success text-wrap" style="width: 5rem;">
                                            in process
                                        </span>
                                        <span class="badge bg-secondary text-wrap" style="width: 5rem;">
                                            ended
                                        </span>
                                    </p>
                                </div>
                            </div>
                            <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
                                <div class="form-col">
                                    <p><b>Start date:</b><span> 05/20/21</span></p>
                                    <p><b>End date:</b><span> 05/23/21</span></p>
                                </div>
                            </div>
                            <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12 my-auto">
                                <div class="form-col text-right">
                                    <a href="#"><button type="button" id="submit" name="submit" class="btn btn-primary mx-1 mt-1">See-></button></a>
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