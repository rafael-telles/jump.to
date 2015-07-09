<%@tag description="Wrapper body" pageEncoding="UTF-8"%>

<%@attribute name="cardtitle" fragment="true" %>
<%@attribute name="cardcontent" fragment="true" %>
<%@attribute name="cardlink" fragment="true" %>



<html>
    <body>
            <header class="section__play-btn mdl-cell mdl-cell--3-col-desktop mdl-cell--2-col-tablet mdl-cell--4-col-phone mdl-color--teal-100 mdl-color-text--white">
              <i class="material-icons">play_circle_filled</i>
            </header>
            <div class="mdl-card mdl-cell mdl-cell--9-col-desktop mdl-cell--6-col-tablet mdl-cell--4-col-phone">
              <div class="mdl-card__supporting-text">
                <h4><jsp:invoke fragment="cardtitle"/></h4>
                <jsp:invoke fragment="cardcontent"/>
              </div>
              <div class="mdl-card__actions">
                <a href="#" class="mdl-button"><jsp:invoke fragment="cardlink"/></a>
              </div>
            </div>
    </body>
</html>