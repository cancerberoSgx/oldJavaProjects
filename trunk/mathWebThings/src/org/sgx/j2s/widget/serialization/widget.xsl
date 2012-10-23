<!-- 
xml to xhtml xsl transformation for widget objects
@author: sgurin

proyecto
 -->
 
<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<!-- widget template -->
<xsl:template match="//Widget">
	<span style="background-color:<xsl:value-of select="//Widget#background-color"/>">
	</span>
</xsl:template>

</xsl:stylesheet> 