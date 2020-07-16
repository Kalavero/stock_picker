<%@ page import="java.util.ArrayList" %>
<%@ page import="model.User" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

    <title>Comparador de ativos</title>
  </head>
  <body>
  <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <a class="navbar-brand" href="#">
      Comparador de Ativos
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
      <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
          <li class="nav-item active">
            <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="./users">Usuários</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="./stocks">Ativos</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="./wallets">Carteiras</a>
          </li>
        </ul>
      </div>
  </nav>

  <div class="container-fluid">
    <h2>Usuários</h2>
    <table class="table">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">Nome</th>
          <th scope="col">Email</th>
          <th scope="col">CPF</th>
          <th scope="col">Data de nascimento</th>
        </tr>
      </thead>
      <tbody>
        <%
          User user1 = new User();
          user1.setId(1);
          user1.setName("Andre");
          user1.setEmail("kalavero@example.com");
          user1.setCpf("334.338.098-90");
          user1.setBirthday("14/11/1991");
          ArrayList<User> users = new ArrayList<User>();
          users.add(user1);
          request.setAttribute("users", users);
        %>
        <c:forEach items="${users}" var="user">
        <tr>
          <td><c:out value="${user.getId()}"/></td>
          <td><c:out value="${user.getName()}"/></td>
          <td><c:out value="${user.getEmail()}"/></td>
          <td><c:out value="${user.getCpf()}"/></td>
          <td><c:out value="${user.getBirthday()}"/></td>
        </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>

    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
  </body>
</html>
