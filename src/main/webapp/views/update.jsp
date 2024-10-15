<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"  %>


<div>Servlet Multipart</div>


<form method="post" action="update" enctype="multipart/form-data">


Choose a file: <input type="file" name="multiPartServlet"/>
<input type="submit" value="Upload"/>
</form>



 <!-- BEGIN CONTENT -->
 <!--         <div class="col-md-12 col-sm-12">
          
            <div class="content-form-page">
              <div class="row">
                <div class="col-md-7 col-sm-7">
                <c:if test="${alert !=null }">
			<h2 class="alert alert danger">${alert}</h2>
		</c:if>
                  <form  action="${pageContext.request.contextPath }/views/update" method="post" class="form-horizontal form-without-legend" role="form" enctype="multipart/form-data" >
                    <div class="form-group">
                      <label for="fullname" class="col-lg-4 control-label">Fullname <span class="require">*</span></label>
                      <div class="col-lg-8">
                        <input type="text" class="form-control" id="fullname" name="fullname">
                      </div>
                    </div>
                    <div class="form-group">
                      <label for="phone" class="col-lg-4 control-label">Phone <span class="require">*</span></label>
                      <div class="col-lg-8">
                        <input type="text" class="form-control" id="phone" name="phone" >
                        
                      </div>
                    </div>
                   
                    <div class="row">
                      <div class="col-lg-8 col-md-offset-4 padding-left-0 padding-top-20">
                        <button type="submit" class="btn btn-primary">Update</button>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-lg-8 col-md-offset-4 padding-left-0 padding-top-10 padding-right-30">
                        <hr>
                        
                      </div>
                    </div>
                    
                    <div>
						<input type="file" name="multiPartServlet" /> 
						<input type="submit" value="Upload" />
					</div>
                    
                  </form>
                </div>
                <div class="col-md-4 col-sm-4 pull-right">
                  <div class="form-info">
                    <h2><em>Important</em> Information</h2>
                    <p>Duis autem vel eum iriure at dolor vulputate velit esse vel molestie at dolore.</p>

                    <button type="button" class="btn btn-default">More details</button>
                  </div>
                </div>
              </div>
            </div>
          </div> -->
          <!-- END CONTENT -->











