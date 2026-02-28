<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Course" %>

<!DOCTYPE html>
<html>
<head>
    <title>Available Courses</title>

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

        .course-card {
            border-radius: 16px;
            transition: transform 0.2s ease;
        }

        .course-card:hover {
            transform: translateY(-4px);
        }
    </style>
</head>
<body>

<div class="container mt-5">

    <div class="d-flex justify-content-between align-items-center mb-4">
        <h3 class="page-title">Available Courses</h3>
        <a href="student.jsp" class="btn btn-outline-secondary">Back</a>
    </div>

    <%
        List<Course> courses = (List<Course>) request.getAttribute("courses");

        if (courses == null || courses.isEmpty()) {
    %>
        <div class="alert alert-info">
            No courses available at the moment.
        </div>
    <%
        } else {
    %>

    <div class="row">
        <%
            for (Course c : courses) {
        %>

        <div class="col-md-4 mb-4">
            <div class="card shadow-sm p-3 course-card h-100">
                <div class="card-body d-flex flex-column">

                    <h5 class="card-title"><%= c.getTitle() %></h5>

                    <p class="card-text text-muted">
                        <%= c.getDescription() %>
                    </p>

                    <form action="enroll" method="post" class="mt-auto">
                        <input type="hidden" name="courseId" value="<%= c.getcid() %>">
                        <button type="submit" class="btn btn-primary w-100">
                            Enroll
                        </button>
                    </form>

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