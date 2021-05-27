<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Owner posts</title>
    <link rel="stylesheet" href="css/main-style.css">
    <link rel="stylesheet" href="css/nav-style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" >

    <!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
    <link href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css'>
    <script src='https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js'></script>
    <link href='https://cdnjs.cloudflare.com/ajax/libs/MaterialDesign-Webfont/3.6.95/css/materialdesignicons.css'> -->

    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    
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
                <a href="#" class="nav-item nav-link active">Posts</a>
                <a href="#" class="nav-item nav-link">Contracts</a>
            </div>
        </div>
    </nav>

    <div class="container">
        <div class="row gutters d-flex justify-content-center">
            <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12 mt-3">
                <div class="row gutters row row-cols-1 row-cols-sm-2 row-cols-md-4">
                    <div class="col-xl-3 col-lg-6 col-md-6 col-sm-6 col-12">
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
                    <div class="col-xl-3 col-lg-6 col-md-6 col-sm-6 col-12">
                        <div class="form-group">
                            <label for="term">Term</label>
                            <select class="form-control" id="term">
                                <option>1 day</option>
                                <option>up to 3 days</option>
                                <option>up to 5 days</option>
                                <option>up to 10 days</option>
                                <option>up to 15 days</option>
                              </select>
                        </div>
                    </div>
                    <div class="col-xl-3 col-lg-6 col-md-6 col-sm-6 col-12">
                        <div class="form-group">
                            <label for="address">Address</label>
                            <input type="text" class="form-range form-control" id="address">
                        </div>
                    </div>
                    <div class="col-xl-3 col-lg-6 col-md-6 col-sm-6 col-12">
                        <div class="form-group">
                            <label></label>
                            <div class="text-center">
                                <a href="#"><button type="button" id="submit" name="submit" class="btn btn-primary">Search</button></a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="text-center col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                    <h6 class="pt-5 mb-2 text-primary">Search results</h6>
                </div>
                <div class="card">
                    <div class="card-body">
                        <div class="row g-2">
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                <h6 class="mb-2 text-primary">Post</h6>
                            </div>
                            <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
                                <div class="form-col">
                                    <p><b>Host:</b> Name Surname</p>
                                    <p><b>Pet:</b><span> dog</span></p>
                                    <p><b>Term:</b><span> 5 days</span></p>

                                </div>
                            </div>
                            <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
                                <div class="form-col text-right">
                                    <div class="form-group">
                                        <label for="rating"><b>Rating: 3,3</b></label>
                                        <div class="form-control-static">
                                            <i class="text-warning fa fa-star"></i>
                                            <i class="text-warning fa fa-star"></i>
                                            <i class="text-warning fa fa-star"></i>
                                            <i class="text-secondary fa fa-star"></i>
                                            <i class="text-secondary fa fa-star"></i>
                                        </div>
                                    </div>
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