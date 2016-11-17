package ${packageName}.${className?lower_case};

import com.brainbeanapps.core.mvp.BasePresenter;
import com.brainbeanapps.core.di.scope.UIScope;

import javax.inject.Inject;


@UIScope
public class ${className}Presenter extends BasePresenter<${className}Contract.View> implements ${className}Contract.Presenter{
	
@Inject public ${className}Presenter() {
}

}
