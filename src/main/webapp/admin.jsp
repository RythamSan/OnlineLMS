<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>

<%
User user = (User) session.getAttribute("user");
if (user == null) {
    response.sendRedirect("login.jsp");
    return;
}

String message = (String) request.getAttribute("message");
String status = (String) request.getAttribute("status");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>

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

    .section-title {
        font-weight: 600;
        margin-bottom: 15px;
    }
</style>
</head>

<body>

<div class="container mt-5">

    <!-- Welcome Header -->
    <div class="card shadow-sm p-4 dashboard-card mb-4">
        <h4 class="mb-1">Welcome, <%= user.getName() %></h4>
        <p class="text-muted mb-0">Admin Control Panel</p>
    </div>

    <!-- Status Message -->
    <%
        if (message != null) {
            String alertType = "error".equals(status) ? "alert-danger" : "alert-success";
    %>
        <div class="alert <%= alertType %> alert-dismissible fade show" role="alert">
            <%= message %>
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    <%
        }
    %>

    <div class="row">

        <!-- Add Course Section -->
        <div class="col-md-6 mb-4">
            <div class="card shadow-sm p-4 dashboard-card h-100">
                <h5 class="section-title">Add Course</h5>

                <form action="addCourse" method="post">

                    <div class="mb-3">
                        <input type="text" name="title" class="form-control" 
                               placeholder="Course Title" required>
                    </div>

                    <div class="mb-3">
                        <textarea name="description" class="form-control" rows="3"
                                  placeholder="Course Description" required></textarea>
                    </div>

                    <button type="submit" class="btn btn-primary w-100">
                        Add Course
                    </button>
                </form>

            </div>
        </div>

        <!-- Add Student Section -->
        <div class="col-md-6 mb-4">
            <div class="card shadow-sm p-4 dashboard-card h-100">
                <h5 class="section-title">Add Student</h5>

                <form action="addStudent" method="post">

                    <div class="mb-3">
                        <input type="text" name="name" class="form-control" 
                               placeholder="Student Name" required>
                    </div>

                    <div class="mb-3">
                        <input type="email" name="email" class="form-control" 
                               placeholder="Student Email" required>
                    </div>

                    <div class="mb-3">
                        <input type="password" name="password" class="form-control" 
                               placeholder="Password" required>
                    </div>

                    <button type="submit" class="btn btn-success w-100">
                        Add Student
                    </button>
                </form>

            </div>
        </div>

    </div>

    <!-- Action Buttons -->
    <div class="card shadow-sm p-4 dashboard-card">
        <div class="d-grid gap-3">

            <a href="courses" class="btn btn-outline-primary">
                View All Courses
            </a>

            <a href="viewStudents" class="btn btn-outline-info">
                View All Students
            </a>

            <a href="logout" class="btn btn-outline-danger">
                Logout
            </a>

        </div>
    </div>

</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>