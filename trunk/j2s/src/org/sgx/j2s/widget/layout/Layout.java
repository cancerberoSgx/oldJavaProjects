package org.sgx.j2s.widget.layout;

import org.sgx.j2s.widget.AbstractWidget;
import org.sgx.j2s.widget.Widget;

public interface Layout {
void layout();
Widget getHost();
void setHost(Widget abstractWidget);
}
