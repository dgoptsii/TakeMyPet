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
            <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12 mt-5">
                <div class="card">
                    <div class="card-header">
                        <h6 class="mb-2 text-primary">Post</h6>
                    </div>
                    <div class="card-body">
                        <div class="row gutters">
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="petCategory">Pet</label>
                                    <select class="form-control" id="petCategory">
                                        <option>Small dog</option>
                                        <option>Medium dog</option>
                                        <option>Large dog</option>
                                        <option>Cat</option>
                                      </select>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="daysRange">Term: n days</label>
                                    <input type="range" class="form-range form-control" min="1" max="20" step="1.0" id="daysRange">
                                </div>
                            </div>
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                <div class="text-right">
                                    <a href="#"><button type="button" id="submit" name="submit" class="btn btn-primary">Create</button></a>
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