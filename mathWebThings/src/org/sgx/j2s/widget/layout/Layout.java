package org.sgx.j2s.widget.layout;

import org.sgx.j2s.widget.Widget;
import org.sgx.j2s.widget.IWidget;

public interface Layout {
void layout();
IWidget getHost();
void setHost(IWidget abstractWidget);
}
