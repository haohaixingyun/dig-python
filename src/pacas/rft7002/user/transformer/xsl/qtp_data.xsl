<xsl:stylesheet version='1.0'
	xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
	xmlns:qtpRep='http://www.mercury.com/qtp/ObjectRepository'>
	
<xsl:output method="text"/>	

<xsl:strip-space elements="item"/>

<xsl:template match='/'>
  <xsl:apply-templates />
</xsl:template>
  
<xsl:template match='data'>
  <xsl:for-each select='item'>
    <xsl:if test="position() != last()">
      <xsl:value-of select='normalize-space(@key)' />
      <xsl:text>,</xsl:text>
    </xsl:if>
    <xsl:if test="position() = last()">
      <xsl:value-of select='normalize-space(@key)' /><xsl:text>
</xsl:text>
    </xsl:if>
  </xsl:for-each>
  <xsl:for-each select='item'>
    <xsl:if test="position() != last()">
     <xsl:value-of select='normalize-space(child::text())' />
     <xsl:text>,</xsl:text>
    </xsl:if>
    <xsl:if test="position() = last()">
     <xsl:value-of select='normalize-space(child::text())' />
    </xsl:if>
   </xsl:for-each>
</xsl:template>
</xsl:stylesheet>