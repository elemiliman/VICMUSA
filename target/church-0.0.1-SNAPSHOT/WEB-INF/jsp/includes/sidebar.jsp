<div class="col-sm-4 col-md-3 sidebar">
    <div class="mini-submenu">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </div>
    <div class="list-group">
        <span href="#" class="list-group-item active">
            Settings
            <span class="pull-right" id="slide-submenu">
                <i class="fa fa-times"></i>
            </span>
        </span>
        <a href="/users/<sec:authentication property='principal.user.userName' />/profile" class="list-group-item">
            <span class="glyphicon glyphicon-user"></span> &nbsp;&nbsp;Edit Profile
        </a>
        <a href="/users/<sec:authentication property='principal.user.userName' />/account" class="list-group-item">
            <span class="glyphicon glyphicon-lock"></span> &nbsp;&nbsp;Account
        </a>
        <a href="/users/<sec:authentication property='principal.user.userName' />/notification" class="list-group-item">
            <span class="glyphicon glyphicon-envelope"></span> &nbsp;&nbsp;Notifications
        </a>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
        <a href="<c:url value='/users/all'/>" class="list-group-item">
            <span class="glyphicon glyphicon-list-alt"></span> &nbsp;&nbsp;Users
        </a>
        </sec:authorize>
        
        <sec:authorize access="hasRole('ROLE_EDITOR') or hasRole('ROLE_ADMIN')">
        <a href="<c:url value='/upload-gallery'/>" class="list-group-item">
            <i class="fa fa-upload" aria-hidden="true"></i> &nbsp;&nbsp;Gallery Upload
        </a>
        </sec:authorize>
        
        <sec:authorize access="hasRole('ROLE_CONTRIBUTOR') or hasRole('ROLE_EDITOR') or hasRole('ROLE_ADMIN')">
          <a href="#user-blog" class="list-group-item" data-toggle="collapse" data-parent="#MainMenu">Blog&nbsp;&nbsp;<i class="fa fa-caret-down"></i></a>
          <div class="collapse" id="user-blog">
            <a href="/blog/create" class="list-group-item"><i class="fa fa-plus-square" aria-hidden="true"></i>&nbsp;&nbsp;Create</a>
            <a href="/blog/published" class="list-group-item"><span class="glyphicon glyphicon-bookmark"></span> &nbsp;&nbsp;Published</a>
            <a href="/blog/drafts" class="list-group-item"><span class="glyphicon glyphicon-pencil"></span> &nbsp;&nbsp;Drafts</a>
          </div>
          </sec:authorize>
        
        <sec:authorize access="hasRole('ROLE_EDITOR') or hasRole('ROLE_ADMIN')">
          <a href="#user-sermon" class="list-group-item" data-toggle="collapse" data-parent="#MainMenu">Sermon&nbsp;&nbsp;<i class="fa fa-caret-down"></i></a>
          <div class="collapse" id="user-sermon">
            <a href="/sermon/create" class="list-group-item"><i class="fa fa-plus-square" aria-hidden="true"></i>&nbsp;&nbsp;Create</a>
            <a href="/sermon/published" class="list-group-item"><span class="glyphicon glyphicon-bookmark"></span> &nbsp;&nbsp;Published</a>
            <a href="/sermon/drafts" class="list-group-item"><span class="glyphicon glyphicon-pencil"></span> &nbsp;&nbsp;Drafts</a>
          </div>
         </sec:authorize>
         
         <sec:authorize access="hasRole('ROLE_EDITOR') or hasRole('ROLE_ADMIN')">
          <a href="#user-event" class="list-group-item" data-toggle="collapse" data-parent="#MainMenu">Event&nbsp;&nbsp;<i class="fa fa-caret-down"></i></a>
          <div class="collapse" id="user-event">
            <a href="/event/create" class="list-group-item"><i class="fa fa-plus-square" aria-hidden="true"></i>&nbsp;&nbsp;Create</a>
            <a href="/event/view" class="list-group-item"><span class="glyphicon glyphicon-edit"></span> &nbsp;&nbsp;Edit</a>
          </div>
         </sec:authorize>
         
          <sec:authorize access="hasRole('ROLE_EDITOR') or hasRole('ROLE_ADMIN')">
          <a href="#user-team" class="list-group-item" data-toggle="collapse" data-parent="#MainMenu">Team&nbsp;&nbsp;<i class="fa fa-caret-down"></i></a>
          <div class="collapse" id="user-team">
            <a href="/team/create" class="list-group-item"><i class="fa fa-plus-square" aria-hidden="true"></i>&nbsp;&nbsp;Create</a>
            <a href="/team/view" class="list-group-item"><span class="glyphicon glyphicon-edit"></span> &nbsp;&nbsp;Edit</a>
          </div>
         </sec:authorize>
    </div>
</div>