package ${packageName};

import android.view.ViewGroup;

import com.brainbeanapps.core.ui.presentation.BaseListAdapter;
import com.brainbeanapps.core.ui.presentation.ViewHolder;

import ${applicationPackage}.R;
import ${dataPackageName}.${dataClassName};
import ${applicationPackage}.databinding.Item${dataClassName}Binding;



public class ${className}Adapter extends BaseListAdapter<${dataClassName}, Item${dataClassName}Binding>{

    @Override public ViewHolder<Item${dataClassName}Binding> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(getLayoutInflater(parent.getContext()).inflate(
                R.layout.item_${classToResource(dataClassName)}, parent, false));
    }

    @Override public void onBindViewHolder(ViewHolder<Item${dataClassName}Binding> holder, int position) {
        holder.getBinding().set${dataClassName}(getItem(position));
    }
}

