<div id="sidebar-wrapper">
        <ul class="sidebar-nav">
        	<li class="sidebar-brand">
            	<p><strong>Personal settings</strong></p>
            </li>
            
            <li>
                <a href="/users/<sec:authentication property='principal.user.userId' />/profile"><span class="glyphicon glyphicon-user"></span> &nbsp;&nbsp;Profile</a>
            </li>
         
            <li>
                <a href="/users/<sec:authentication property='principal.user.userId' />/account"><span class="glyphicon glyphicon-lock"></span> &nbsp;&nbsp;Account</a>
            </li>
            <li>
                <a href="/users/<sec:authentication property='principal.user.userId' />/notification"><span class="glyphicon glyphicon-envelope"></span> &nbsp;&nbsp;Notifications</a>
            </li>
            
            <sec:authorize access="hasRole('ROLE_ADMIN')">
            <li>
                <a href="/users/all"><span class="glyphicon glyphicon-list-alt"></span> &nbsp;&nbsp;VICM Users</a>
            </li>
            </sec:authorize>
            
            <sec:authorize access="hasRole('ROLE_EDITOR') or hasRole('ROLE_ADMIN')">
            <li>
                <a href="/upload-gallery"><span class="glyphicon glyphicon-picture"></span> &nbsp;&nbsp;Gallery Upload</a>
            </li>
            </sec:authorize>
            
            <sec:authorize access="hasRole('ROLE_CONTRIBUTOR') or hasRole('ROLE_EDITOR') or hasRole('ROLE_ADMIN')">
            <li class="dropdown">
            	<a href="#" class="dropdown-toggle" data-toggle="dropdown">
                     Blog <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                	<li><a href="/blog/create"><span class="glyphicon glyphicon-pencil"></span> &nbsp;&nbsp;Create</a></li>
                	<li><a href="/blog/published"><span class="glyphicon glyphicon-bookmark"></span> &nbsp;&nbsp;Published</a></li>
                	<li><a href="/blog/drafts"><span class="glyphicon glyphicon-pencil"></span> &nbsp;&nbsp;Drafts</a></li>
                </ul>
            </li>
            </sec:authorize>
            
            <sec:authorize access="hasRole('ROLE_EDITOR') or hasRole('ROLE_ADMIN')">
            <li class="dropdown">
            	<a href="#" class="dropdown-toggle" data-toggle="dropdown">
                     Sermon <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                	<li><a href="/sermon/create"><span class="glyphicon glyphicon-pencil"></span> &nbsp;&nbsp;Create</a></li>
                	<li><a href="/sermon/published"><span class="glyphicon glyphicon-bookmark"></span> &nbsp;&nbsp;Published</a></li>
                	<li><a href="/sermon/drafts"><span class="glyphicon glyphicon-pencil"></span> &nbsp;&nbsp;Drafts</a></li>
                </ul>
            </li>
            </sec:authorize>
            
       </ul>
    </div>
    <!-- /#sidebar-wrapper -->