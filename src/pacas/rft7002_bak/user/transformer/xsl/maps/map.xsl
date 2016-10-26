<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet 
   xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
   version="1.0">
   <xsl:strip-space elements="*"/>
   <xsl:output method="xml"/>

         <xsl:template match="object">
            <xsl:text>
            </xsl:text>
            <xsl:element name="{normalize-space(@name)}">
                  <xsl:for-each select="prop">
                     <xsl:attribute name="{normalize-space(@name)}">
                        <xsl:value-of select="normalize-space(text())"/>
                     </xsl:attribute>
                  </xsl:for-each>
               <xsl:apply-templates/>
            </xsl:element>
           
         </xsl:template>
   <!-- ingore all prop nodes -->
   <xsl:template match="prop" />
</xsl:stylesheet>