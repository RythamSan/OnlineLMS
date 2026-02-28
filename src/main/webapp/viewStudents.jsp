<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>

<!DOCTYPE html>
<html>
<head>
    <title>All Students</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f4f6f9;
            font-family: 'Segoe UI', sans-serif;
        }

        .page-title {
            font-weight: 600;
        }

        .student-card {
            border-radius: 16px;
            transition: transform 0.2s ease;
        }

        .student-card:hover {
            transform: translateY(-4px);
        }
    </style>
</head>
<body>

<div class="container mt-5">

    <!-- Header -->
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h3 class="page-title">All Students</h3>
        <a href="admin.jsp" class="btn btn-outline-secondary">
            Back to Dashboard
        </a>
    </div>

    <%
        List<User> students = (List<User>) request.getAttribute("students");

        if (students == null || students.isEmpty()) {
    %>
        <div class="alert alert-warning">
            No students found in the system.
        </div>
    <%
        } else {
    %>

    <div class="row">
        <%
            for (User student : students) {
        %>

        <div class="col-md-4 mb-4">
            <div class="card shadow-sm p-3 student-card h-100">
                <div class="card-body">

                    <h5 class="card-title"><%= student.getName() %></h5>

                    <p class="card-text text-muted mb-1">
                        <strong>Email:</strong> <%= student.getEmail() %>
                    </p>

                    <p class="card-text text-muted">
                        <strong>Role:</strong> <%= student.getRole() %>
                    </p>

                </div>
            </div>
        </div>

        <%
            }
        %>
    </div>

    <%
        }
    %>

</div>

</body>
</html>