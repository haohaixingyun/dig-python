<xsl:stylesheet version='1.0'
	xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
	xmlns:qtpRep='http://www.mercury.com/qtp/ObjectRepository'>
	<xsl:output cdata-section-elements="qtpRep:Value"/>
	<xsl:template name="imageTemplate">
	  <xsl:param name="paramPropertyName">default</xsl:param>
	  <xsl:param name="paramIndex">default</xsl:param>
	  <xsl:param name="paramName">default</xsl:param>
	  <xsl:param name="paramValue">default</xsl:param>
	  <xsl:text>
</xsl:text><qtpRep:Object qtpRep:Class="Image" qtpRep:Name="{$paramName}"><xsl:text>
</xsl:text>
<qtpRep:Properties><xsl:text>
</xsl:text>
<qtpRep:Property qtpRep:Name="micclass" qtpRep:Hidden="1" qtpRep:ReadOnly="1" qtpRep:Type="STRING">	<qtpRep:Value qtpRep:RegularExpression="0">Image</qtpRep:Value>	</qtpRep:Property><xsl:text>
</xsl:text>
<qtpRep:Property qtpRep:Name="image type" qtpRep:Hidden="0" qtpRep:ReadOnly="0" qtpRep:Type="STRING">	<qtpRep:Value qtpRep:RegularExpression="0">Image Button</qtpRep:Value>	</qtpRep:Property><xsl:text>
</xsl:text>
<qtpRep:Property qtpRep:Name="html tag" qtpRep:Hidden="0" qtpRep:ReadOnly="0" qtpRep:Type="STRING">	<qtpRep:Value qtpRep:RegularExpression="0">INPUT</qtpRep:Value>	</qtpRep:Property><xsl:text>
</xsl:text>
               <xsl:if test="$paramPropertyName='.alt'">
<qtpRep:Property qtpRep:Name="alt" qtpRep:Hidden="0" qtpRep:ReadOnly="0" qtpRep:Type="STRING">	<qtpRep:Value qtpRep:RegularExpression="0"><xsl:value-of select="$paramValue"/></qtpRep:Value>	</qtpRep:Property><xsl:text>
</xsl:text>
               </xsl:if>
               <xsl:if test="$paramPropertyName='.value'">
<qtpRep:Property qtpRep:Name="value" qtpRep:Hidden="0" qtpRep:ReadOnly="0" qtpRep:Type="STRING">	<qtpRep:Value qtpRep:RegularExpression="0"><xsl:value-of select="$paramValue"/></qtpRep:Value>	</qtpRep:Property><xsl:text>
</xsl:text>
               </xsl:if>
               <xsl:if test="$paramPropertyName='.title'">
<qtpRep:Property qtpRep:Name="title" qtpRep:Hidden="0" qtpRep:ReadOnly="0" qtpRep:Type="STRING">	<qtpRep:Value qtpRep:RegularExpression="0"><xsl:value-of select="$paramValue"/></qtpRep:Value>	</qtpRep:Property><xsl:text>
</xsl:text>
               </xsl:if>
</qtpRep:Properties><xsl:text>
</xsl:text>
<qtpRep:BasicIdentification><xsl:text>
</xsl:text>
<qtpRep:PropertyRef>micclass</qtpRep:PropertyRef><xsl:text>
</xsl:text>
<qtpRep:PropertyRef>image type</qtpRep:PropertyRef><xsl:text>
</xsl:text>
<qtpRep:PropertyRef>html tag</qtpRep:PropertyRef><xsl:text>
</xsl:text>
               <xsl:if test="$paramPropertyName='.alt'">
<qtpRep:PropertyRef>alt</qtpRep:PropertyRef><xsl:text>
</xsl:text>
               </xsl:if>
               <xsl:if test="$paramPropertyName='.value'">
<qtpRep:PropertyRef>value</qtpRep:PropertyRef><xsl:text>
</xsl:text>
               </xsl:if>
               <xsl:if test="$paramPropertyName='.title'">
<qtpRep:PropertyRef>title</qtpRep:PropertyRef><xsl:text>
</xsl:text>
               </xsl:if>
<qtpRep:OrdinalIdentifier qtpRep:Type="index">	<qtpRep:Value><xsl:value-of select="$paramIndex" /></qtpRep:Value>	</qtpRep:OrdinalIdentifier><xsl:text>
</xsl:text>
</qtpRep:BasicIdentification><xsl:text>
</xsl:text>
<qtpRep:SmartIdentification qtpRep:Algorithm="Mercury.TolerantPriority" qtpRep:Active="1"><xsl:text>
</xsl:text>
<qtpRep:BaseFilter><xsl:text>
</xsl:text>
<qtpRep:PropertyRef>micclass</qtpRep:PropertyRef><xsl:text>
</xsl:text>
<qtpRep:PropertyRef>html tag</qtpRep:PropertyRef><xsl:text>
</xsl:text>
</qtpRep:BaseFilter><xsl:text>
</xsl:text>
<qtpRep:OptionalFilter><xsl:text>
</xsl:text>
<qtpRep:PropertyRef>image type</qtpRep:PropertyRef><xsl:text>
</xsl:text>
               <xsl:if test="$paramPropertyName='.alt'">
<qtpRep:PropertyRef>alt</qtpRep:PropertyRef><xsl:text>
</xsl:text>
               </xsl:if>
               <xsl:if test="$paramPropertyName='.value'">
<qtpRep:PropertyRef>value</qtpRep:PropertyRef><xsl:text>
</xsl:text>
               </xsl:if>
               <xsl:if test="$paramPropertyName='.title'">
<qtpRep:PropertyRef>title</qtpRep:PropertyRef><xsl:text>
</xsl:text>
               </xsl:if>
</qtpRep:OptionalFilter><xsl:text>
</xsl:text>
</qtpRep:SmartIdentification><xsl:text>
</xsl:text>
<qtpRep:LastUpdateTime></qtpRep:LastUpdateTime><xsl:text>
</xsl:text>
<qtpRep:ChildObjects><xsl:text>
</xsl:text>
</qtpRep:ChildObjects><xsl:text>
</xsl:text>
</qtpRep:Object>
	</xsl:template>
</xsl:stylesheet>