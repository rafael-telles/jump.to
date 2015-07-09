<%@tag description="Wrapper Header" pageEncoding="UTF-8"%>
<%@attribute name="tabbarcontent" fragment="true" %>
<%@attribute name="tabbarnavcontent" fragment="true" %>

<html>
     <body>
        <header class="mdl-layout__header mdl-layout__header--scroll mdl-color--primary">
            <div class="mdl-layout--large-screen-only mdl-layout__header-row">
            </div>
            <div class="mdl-layout--large-screen-only mdl-layout__header-row">
              <h3>Url Shortener</h3>
            </div>
            <div class="mdl-layout--large-screen-only mdl-layout__header-row">
            </div>
            <div class="mdl-layout__tab-bar mdl-js-ripple-effect mdl-color--primary-dark">
                 <jsp:invoke fragment="tabbarcontent"/>
              <button class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--colored mdl-shadow--4dp mdl-color--accent" id="add">
                <i class="material-icons">add</i>
              </button>
            </div>
          </header>
          <div class="mdl-layout__drawer">
        <span class="mdl-layout-title">Url Shortener</span>
        <nav class="mdl-navigation">
            <jsp:invoke fragment="tabbarnavcontent"/>
        </nav>
      </div>
    </body>
</html>