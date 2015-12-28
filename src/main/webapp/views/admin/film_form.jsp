<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../templates/header.jsp"%>
<link rel="stylesheet" href="<c:url value='/static/styles/common.css' />" />
</head>
<%@ include file="../templates/navbar.jsp" %>

<div class="container">
  <div class="row">
    <div class="col-sm-12">
      <form id="filmForm" class="form-horizontal" enctype="multipart/form-data">

        <div class="form-group">
          <label for="inputFilmName" class="control-label col-sm-2">影片名称</label>
          <div class="col-sm-9">
            <input type="text" id="inputFilmName" class="form-control" name="filmName">
          </div>
        </div>

        <div class="form-group">
          <label for="inputDirector" class="control-label col-sm-2">导演</label>
          <div class="col-sm-9">
            <input type="text" id="inputDirector" class="form-control" name="director">
          </div>
        </div>

        <div class="form-group">
          <label for="inputActors" class="control-label col-sm-2">演员</label>
          <div class="col-sm-9">
            <input type="text" id="inputActors" class="form-control" name="actors">
          </div>
        </div>

        <div class="form-group">
          <label for="inputPoster" class="control-label col-sm-2">影片海报</label>
          <div class="col-sm-9">
            <input type="file" id="inputPoster" class="form-control" name="poster">
          </div>
        </div>

        <div class="form-group">
          <div class="col-sm-offset-3 col-sm-9">
            <button class="btn btn-primary ajax-submit">提交</button>
          </div>
        </div>
        <div class="col-sm-12" style="display: none;" id="response">
          <div class="alert alert-danger alert-dismissible fade in" role="alert" >
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
            <p id="alert-text"></p>
          </div>
        </div>
        <div class="clearfix"></div>

      </form>
    </div>
  </div>
</div>

<%@ include file="../templates/footer.jsp"%>
<script>
  $(document).ready(function() {
    $('.ajax-submit').click(function() {
      var url = "<%=basePath%>/films/add";
      var formData = new FormData($('#filmForm')[0]);
      $.ajax({
        url: url,
        type: "post",
        data: formData,
        processData: false,
        contentType: false,
        success: function() {
          alert("上传");
        }
      });
      return false;
    });
  });
</script>
</body>
</html>