package br.com.mecanica.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.mecanica.mb.UsuarioBean;

@SuppressWarnings("serial")
public class Autorizador implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext context = event.getFacesContext();
		
		if("/login.xhtml".equals(context.getViewRoot().getViewId()))
			return;
		
		UsuarioBean mb = context.getApplication().evaluateExpressionGet
				(context, "#{usuarioBean}", UsuarioBean.class);
		
		if(!mb.isLogado()) {
			NavigationHandler nh = context.getApplication().getNavigationHandler();
			nh.handleNavigation(context, null, "login?faces-redirect=true");
			context.renderResponse();
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}


}
