<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>

<%
User user = (User) session.getAttribute("user");
if (user == null) {
    response.sendRedirect("login.jsp");
    return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Dashboard</title>

<!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<style>
    body {
        background-color: #f4f6f9;
        font-family: 'Segoe UI', sans-serif;
    }

    .dashboard-card {
        border-radius: 18px;
    }

    .welcome-text {
        font-weight: 600;
    }

    .btn-lg {
        border-radius: 12px;
    }
</style>
</head>

<body>

<div class="container mt-5">

    <!-- Welcome Section -->
    <div class="card shadow-sm p-4 dashboard-card mb-4">
        <h4 class="welcome-text">
            Welcome, <%= user.getName() %>
        </h4>
        <p class="text-muted mb-0">Manage your learning journey</p>
    </div>

    <!-- Action Buttons -->
    <div class="card shadow-sm p-4 dashboard-card">

        <div class="d-grid gap-3">

            <a href="courses" class="btn btn-primary btn-lg">
                View Available Courses
            </a>

            <a href="my-courses" class="btn btn-success btn-lg">
                My Courses
            </a>

            <a href="logout" class="btn btn-outline-danger btn-lg">
                Logout
            </a>

        </div>

    </div>

</div>

</body>
</html>