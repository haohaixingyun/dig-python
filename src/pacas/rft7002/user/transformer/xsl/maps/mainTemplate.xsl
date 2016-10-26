<xsl:stylesheet version='1.0'
	xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
	xmlns:qtpRep='http://www.mercury.com/qtp/ObjectRepository'>
	<xsl:output cdata-section-elements="qtpRep:Value" />
	
	<!--import the element template stylesheets starts-->
	<xsl:import href="textTemplate.xsl"/>
	<xsl:import href="imageTemplate.xsl"/>
	<xsl:import href="passwordTemplate.xsl"/>
	<xsl:import href="hyperlinkTemplate.xsl"/>
	<xsl:import href="selectTemplate.xsl"/>
	<xsl:import href="submitTemplate.xsl"/>
	<xsl:import href="selectTemplate.xsl"/>
	<xsl:import href="tableTemplate.xsl"/>
	<xsl:import href="radioTemplate.xsl"/>
	<xsl:import href="checkboxTemplate.xsl"/>
	<xsl:import href="textareaTemplate.xsl"/>
	<!--import the element template stylesheets ends-->
	
	<xsl:output method="text" indent="yes"/>
	
	<xsl:template name="mainTemplate">

       <xsl:param name="name" />
       <xsl:param name="value" />
       <xsl:param name="propertyName" />
       <xsl:param name="classID" />
       <xsl:param name="index" />
       
       <xsl:choose>      

         <xsl:when test="$classID='Html.INPUT.image'">
              <xsl:call-template name="imageTemplate">
                 <xsl:with-param name="paramPropertyName" select="$propertyName" />
                 <xsl:with-param name="paramName" select="$name" />
                 <xsl:with-param name="paramValue" select="$value" />
                 <xsl:with-param name="paramIndex" select="$index" />
              </xsl:call-template>
         </xsl:when>
          
          <xsl:when test="$classID='Html.INPUT.password'">
              <xsl:call-template name="passwordTemplate">
                  <xsl:with-param name="paramPropertyName" select="$propertyName" />
                 <xsl:with-param name="paramName" select="$name" />
                 <xsl:with-param name="paramValue" select="$value" />
                 <xsl:with-param name="paramIndex" select="$index" />
              </xsl:call-template>
          </xsl:when>
          
          <xsl:when test="$classID='Html.INPUT.text'">
              <xsl:call-template name="textTemplate">
                  <xsl:with-param name="paramPropertyName" select="$propertyName" />
                 <xsl:with-param name="paramName" select="$name" />
                 <xsl:with-param name="paramValue" select="$value" />
                 <xsl:with-param name="paramIndex" select="$index" />
              </xsl:call-template>
          </xsl:when>
          
          <xsl:when test="$classID='Html.A'">
              <xsl:call-template name="hyperlinkTemplate">
                  <xsl:with-param name="paramPropertyName" select="$propertyName" />
                 <xsl:with-param name="paramName" select="$name" />
                 <xsl:with-param name="paramValue" select="$value" />
                 <xsl:with-param name="paramIndex" select="$index" />
              </xsl:call-template>
          </xsl:when>
          
          <xsl:when test="$classID='Html.INPUT.submit'">
              <xsl:call-template name="submitTemplate">
                 <xsl:with-param name="paramPropertyName" select="$propertyName" />
                 <xsl:with-param name="paramName" select="$name" />
                 <xsl:with-param name="paramValue" select="$value" />
                 <xsl:with-param name="paramIndex" select="$index" />
              </xsl:call-template>
          </xsl:when>
          
          <xsl:when test="$classID='Html.SELECT'">
              <xsl:call-template name="selectTemplate">
                  <xsl:with-param name="paramPropertyName" select="$propertyName" />
                 <xsl:with-param name="paramName" select="$name" />
                 <xsl:with-param name="paramValue" select="$value" />
                 <xsl:with-param name="paramIndex" select="$index" />
              </xsl:call-template>
          </xsl:when>
          
          <xsl:when test="$classID='Html.TABLE'">
              <xsl:call-template name="tableTemplate">
                  <xsl:with-param name="paramPropertyName" select="$propertyName" />
                 <xsl:with-param name="paramName" select="$name" />
                 <xsl:with-param name="paramValue" select="$value" />
                 <xsl:with-param name="paramIndex" select="$index" />
              </xsl:call-template>
          </xsl:when>
          
          <xsl:when test="$classID='Html.INPUT.radio'">
              <xsl:call-template name="radioTemplate">
                  <xsl:with-param name="paramPropertyName" select="$propertyName" />
                 <xsl:with-param name="paramName" select="$name" />
                 <xsl:with-param name="paramValue" select="$value" />
                 <xsl:with-param name="paramIndex" select="$index" />
              </xsl:call-template>
          </xsl:when>
          
          <xsl:when test="$classID='Html.INPUT.checkbox'">
              <xsl:call-template name="checkboxTemplate">
                  <xsl:with-param name="paramPropertyName" select="$propertyName" />
                 <xsl:with-param name="paramName" select="$name" />
                 <xsl:with-param name="paramValue" select="$value" />
                 <xsl:with-param name="paramIndex" select="$index" />
              </xsl:call-template>
          </xsl:when>
          
          <xsl:when test="$classID='Html.INPUT.TEXTAREA'">
              <xsl:call-template name="textareaTemplate">
                  <xsl:with-param name="paramPropertyName" select="$propertyName" />
                 <xsl:with-param name="paramName" select="$name" />
                 <xsl:with-param name="paramValue" select="$value" />
                 <xsl:with-param name="paramIndex" select="$index" />
              </xsl:call-template>
          </xsl:when>
       </xsl:choose>
	</xsl:template>
</xsl:stylesheet>