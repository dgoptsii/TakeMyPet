<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Host contracts</title>
    <link rel="stylesheet" href="css/main-style.css">
    <link rel="stylesheet" href="css/nav-style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" >
    <!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script> -->
    <!-- <link href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css'> -->
    <!-- <script src='https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js'></script> -->
    <!-- <link href='https://cdnjs.cloudflare.com/ajax/libs/MaterialDesign-Webfont/3.6.95/css/materialdesignicons.css'> -->

    <!-- nav animation button(both scripts)! -->
    <!-- order of scripts is important(jquery, than bootstrap js)! -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" crossorigin="anonymous"></script>
    
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
                <a href="#" class="nav-item nav-link active">Contracts</a>
            </div>
        </div>
    </nav>

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