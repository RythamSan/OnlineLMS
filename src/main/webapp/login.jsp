<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LMS Login</title>

<!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<style>
    body {
        background: linear-gradient(135deg, #f8f9fa, #e9ecef);
        height: 100vh;
        display: flex;
        align-items: center;
        justify-content: center;
        font-family: 'Segoe UI', sans-serif;
    }

    .login-card {
        width: 100%;
        max-width: 400px;
        border-radius: 16px;
    }

    .brand-title {
        font-weight: 600;
        letter-spacing: 1px;
    }
</style>
</head>

<body>

<div class="card shadow-sm p-4 login-card">
    
    <div class="text-center mb-4">
        <h3 class="brand-title">LMS Portal</h3>
        <p class="text-muted small">Sign in to continue</p>
    </div>

    <form action="login" method="post">

        <div class="mb-3">
            <label class="form-label">Email</label>
            <input type="email" name="email" class="form-control" required>
        </div>

        <div class="mb-4">
            <label class="form-label">Password</label>
            <input type="password" name="password" class="form-control" required>
        </div>

        <button type="submit" class="btn btn-primary w-100">
            Login
        </button>

    </form>

</div>

</body>
</html>





