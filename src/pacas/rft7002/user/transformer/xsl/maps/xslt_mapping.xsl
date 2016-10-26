<?xml version='1.0' ?>
<xsl:stylesheet version='1.0'
	xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
	xmlns:qtpRep='http://www.mercury.com/qtp/ObjectRepository'>
	<xsl:output cdata-section-elements="qtpRep:Value"/>
	
	<xsl:import href="mainTemplate.xsl"/>
	
    <xsl:template match='object[@name = "common"]'>	
    <xsl:text>
</xsl:text><qtpRep:ObjectRepository xmlns:qtpRep="http://www.mercury.com/qtp/ObjectRepository"><xsl:text>
</xsl:text>
<qtpRep:Objects><xsl:text>
</xsl:text>
<qtpRep:Object qtpRep:Class="Browser" qtpRep:Name="Browser"><xsl:text>
</xsl:text>
<qtpRep:Properties><xsl:text>
</xsl:text>
<qtpRep:Property qtpRep:Name="title" qtpRep:Hidden="1" qtpRep:ReadOnly="1" qtpRep:Type="STRING">	<qtpRep:Value qtpRep:RegularExpression="0">title</qtpRep:Value>	</qtpRep:Property><xsl:text>
</xsl:text>
<qtpRep:Property qtpRep:Name="name" qtpRep:Hidden="1" qtpRep:ReadOnly="1" qtpRep:Type="STRING">	<qtpRep:Value qtpRep:RegularExpression="0"></qtpRep:Value>	</qtpRep:Property><xsl:text>
</xsl:text>
<qtpRep:Property qtpRep:Name="micclass" qtpRep:Hidden="1" qtpRep:ReadOnly="1" qtpRep:Type="STRING">	<qtpRep:Value qtpRep:RegularExpression="0">Browser</qtpRep:Value>	</qtpRep:Property><xsl:text>
</xsl:text>
<qtpRep:Property qtpRep:Name="browserindex" qtpRep:Hidden="1" qtpRep:ReadOnly="1" qtpRep:Type="I2">	<qtpRep:Value qtpRep:RegularExpression="0">1</qtpRep:Value>	</qtpRep:Property><xsl:text>
</xsl:text>
</qtpRep:Properties><xsl:text>
</xsl:text>
<qtpRep:BasicIdentification><xsl:text>
</xsl:text>
<qtpRep:PropertyRef>micclass</qtpRep:PropertyRef><xsl:text>
</xsl:text>
</qtpRep:BasicIdentification><xsl:text>
</xsl:text>
<qtpRep:SmartIdentification qtpRep:Algorithm="Mercury.TolerantPriority" qtpRep:Active="1"><xsl:text>
</xsl:text>
<qtpRep:BaseFilter><xsl:text>
</xsl:text>
<qtpRep:PropertyRef>micclass</qtpRep:PropertyRef><xsl:text>
</xsl:text>
</qtpRep:BaseFilter><xsl:text>
</xsl:text>
<qtpRep:OptionalFilter><xsl:text>
</xsl:text>
<qtpRep:PropertyRef>name</qtpRep:PropertyRef><xsl:text>
</xsl:text>
<qtpRep:PropertyRef>title</qtpRep:PropertyRef><xsl:text>
</xsl:text>
</qtpRep:OptionalFilter><xsl:text>
</xsl:text>
</qtpRep:SmartIdentification><xsl:text>
</xsl:text>
<qtpRep:LastUpdateTime></qtpRep:LastUpdateTime><xsl:text>
</xsl:text>
<qtpRep:ChildObjects><xsl:text>
</xsl:text>
<qtpRep:Object qtpRep:Class="Dialog" qtpRep:Name="Security Alert"><xsl:text>
</xsl:text>
<qtpRep:Properties><xsl:text>
</xsl:text>
<qtpRep:Property qtpRep:Name="text" qtpRep:Hidden="0" qtpRep:ReadOnly="0" qtpRep:Type="STRING">	<qtpRep:Value qtpRep:RegularExpression="0">Security Alert</qtpRep:Value>	</qtpRep:Property><xsl:text>
</xsl:text>
<qtpRep:Property qtpRep:Name="nativeclass" qtpRep:Hidden="0" qtpRep:ReadOnly="0" qtpRep:Type="STRING">	<qtpRep:Value qtpRep:RegularExpression="0">#32770</qtpRep:Value>	</qtpRep:Property><xsl:text>
</xsl:text>
<qtpRep:Property qtpRep:Name="is owned window" qtpRep:Hidden="0" qtpRep:ReadOnly="0" qtpRep:Type="BOOL">	<qtpRep:Value qtpRep:RegularExpression="0">-1</qtpRep:Value>	</qtpRep:Property><xsl:text>
</xsl:text>
<qtpRep:Property qtpRep:Name="is child window" qtpRep:Hidden="0" qtpRep:ReadOnly="0" qtpRep:Type="BOOL">	<qtpRep:Value qtpRep:RegularExpression="0">0</qtpRep:Value>	</qtpRep:Property><xsl:text>
</xsl:text>
</qtpRep:Properties><xsl:text>
</xsl:text>
<qtpRep:BasicIdentification><xsl:text>
</xsl:text>
<qtpRep:PropertyRef>text</qtpRep:PropertyRef><xsl:text>
</xsl:text>
<qtpRep:PropertyRef>nativeclass</qtpRep:PropertyRef><xsl:text>
</xsl:text>
<qtpRep:PropertyRef>is owned window</qtpRep:PropertyRef><xsl:text>
</xsl:text>
<qtpRep:PropertyRef>is child window</qtpRep:PropertyRef><xsl:text>
</xsl:text>
</qtpRep:BasicIdentification><xsl:text>
</xsl:text>
<qtpRep:CustomReplay><xsl:text>
</xsl:text>
<qtpRep:Behavior qtpRep:Name="simclass" qtpRep:Type="STRING">#32770</qtpRep:Behavior><xsl:text>
</xsl:text>
</qtpRep:CustomReplay><xsl:text>
</xsl:text>
<qtpRep:LastUpdateTime>Tuesday, November 13, 2007 4:58:47 PM</qtpRep:LastUpdateTime><xsl:text>
</xsl:text>
<qtpRep:ChildObjects><xsl:text>
</xsl:text>
<qtpRep:Object qtpRep:Class="WinButton" qtpRep:Name="Yes"><xsl:text>
</xsl:text>
<qtpRep:Properties><xsl:text>
</xsl:text>
<qtpRep:Property qtpRep:Name="text" qtpRep:Hidden="0" qtpRep:ReadOnly="0" qtpRep:Type="STRING">	<qtpRep:Value qtpRep:RegularExpression="0">&amp;Yes</qtpRep:Value>	</qtpRep:Property><xsl:text>
</xsl:text>
<qtpRep:Property qtpRep:Name="nativeclass" qtpRep:Hidden="0" qtpRep:ReadOnly="0" qtpRep:Type="STRING">	<qtpRep:Value qtpRep:RegularExpression="0">Button</qtpRep:Value>	</qtpRep:Property><xsl:text>
</xsl:text>
</qtpRep:Properties><xsl:text>
</xsl:text>
<qtpRep:BasicIdentification><xsl:text>
</xsl:text>
<qtpRep:PropertyRef>text</qtpRep:PropertyRef><xsl:text>
</xsl:text>
<qtpRep:PropertyRef>nativeclass</qtpRep:PropertyRef><xsl:text>
</xsl:text>
</qtpRep:BasicIdentification><xsl:text>
</xsl:text>
<qtpRep:CustomReplay><xsl:text>
</xsl:text>
<qtpRep:Behavior qtpRep:Name="simclass" qtpRep:Type="STRING">Button</qtpRep:Behavior><xsl:text>
</xsl:text>
</qtpRep:CustomReplay><xsl:text>
</xsl:text>
<qtpRep:LastUpdateTime>Tuesday, November 13, 2007 4:58:51 PM</qtpRep:LastUpdateTime><xsl:text>
</xsl:text>
<qtpRep:ChildObjects><xsl:text>
</xsl:text>
</qtpRep:ChildObjects><xsl:text>
</xsl:text>
</qtpRep:Object><xsl:text>
</xsl:text>
<qtpRep:Object qtpRep:Class="WinButton" qtpRep:Name="View Certificate"><xsl:text>
</xsl:text>
<qtpRep:Properties><xsl:text>
</xsl:text>
<qtpRep:Property qtpRep:Name="text" qtpRep:Hidden="0" qtpRep:ReadOnly="0" qtpRep:Type="STRING">	<qtpRep:Value qtpRep:RegularExpression="0">&amp;View Certificate</qtpRep:Value>	</qtpRep:Property><xsl:text>
</xsl:text>
<qtpRep:Property qtpRep:Name="nativeclass" qtpRep:Hidden="0" qtpRep:ReadOnly="0" qtpRep:Type="STRING">	<qtpRep:Value qtpRep:RegularExpression="0">Button</qtpRep:Value>	</qtpRep:Property><xsl:text>
</xsl:text>
</qtpRep:Properties><xsl:text>
</xsl:text>
<qtpRep:BasicIdentification><xsl:text>
</xsl:text>
<qtpRep:PropertyRef>text</qtpRep:PropertyRef><xsl:text>
</xsl:text>
<qtpRep:PropertyRef>nativeclass</qtpRep:PropertyRef><xsl:text>
</xsl:text>
</qtpRep:BasicIdentification><xsl:text>
</xsl:text>
<qtpRep:CustomReplay><xsl:text>
</xsl:text>
<qtpRep:Behavior qtpRep:Name="simclass" qtpRep:Type="STRING">Button</qtpRep:Behavior><xsl:text>
</xsl:text>
</qtpRep:CustomReplay><xsl:text>
</xsl:text>
<qtpRep:LastUpdateTime>Tuesday, November 13, 2007 4:58:51 PM</qtpRep:LastUpdateTime><xsl:text>
</xsl:text>
<qtpRep:ChildObjects><xsl:text>
</xsl:text>
</qtpRep:ChildObjects><xsl:text>
</xsl:text>
</qtpRep:Object><xsl:text>
</xsl:text>
<qtpRep:Object qtpRep:Class="WinButton" qtpRep:Name="No"><xsl:text>
</xsl:text>
<qtpRep:Properties><xsl:text>
</xsl:text>
<qtpRep:Property qtpRep:Name="text" qtpRep:Hidden="0" qtpRep:ReadOnly="0" qtpRep:Type="STRING">	<qtpRep:Value qtpRep:RegularExpression="0">&amp;No</qtpRep:Value>	</qtpRep:Property><xsl:text>
</xsl:text>
<qtpRep:Property qtpRep:Name="nativeclass" qtpRep:Hidden="0" qtpRep:ReadOnly="0" qtpRep:Type="STRING">	<qtpRep:Value qtpRep:RegularExpression="0">Button</qtpRep:Value>	</qtpRep:Property><xsl:text>
</xsl:text>
</qtpRep:Properties><xsl:text>
</xsl:text>
<qtpRep:BasicIdentification><xsl:text>
</xsl:text>
<qtpRep:PropertyRef>text</qtpRep:PropertyRef><xsl:text>
</xsl:text>
<qtpRep:PropertyRef>nativeclass</qtpRep:PropertyRef><xsl:text>
</xsl:text>
</qtpRep:BasicIdentification><xsl:text>
</xsl:text>
<qtpRep:CustomReplay><xsl:text>
</xsl:text>
<qtpRep:Behavior qtpRep:Name="simclass" qtpRep:Type="STRING">Button</qtpRep:Behavior><xsl:text>
</xsl:text>
</qtpRep:CustomReplay><xsl:text>
</xsl:text>
<qtpRep:LastUpdateTime>Tuesday, November 13, 2007 4:58:50 PM</qtpRep:LastUpdateTime><xsl:text>
</xsl:text>
<qtpRep:ChildObjects><xsl:text>
</xsl:text>
</qtpRep:ChildObjects><xsl:text>
</xsl:text>
</qtpRep:Object><xsl:text>
</xsl:text>
</qtpRep:ChildObjects><xsl:text>
</xsl:text>
</qtpRep:Object><xsl:text>
</xsl:text>
<qtpRep:Object qtpRep:Class="Dialog" qtpRep:Name="Microsoft Internet Explorer"><xsl:text>
</xsl:text>
<qtpRep:Properties><xsl:text>
</xsl:text>
<qtpRep:Property qtpRep:Name="text" qtpRep:Hidden="0" qtpRep:ReadOnly="0" qtpRep:Type="STRING">	<qtpRep:Value qtpRep:RegularExpression="0">Microsoft Internet Explorer</qtpRep:Value>	</qtpRep:Property><xsl:text>
</xsl:text>
<qtpRep:Property qtpRep:Name="nativeclass" qtpRep:Hidden="0" qtpRep:ReadOnly="0" qtpRep:Type="STRING">	<qtpRep:Value qtpRep:RegularExpression="0">#32770</qtpRep:Value>	</qtpRep:Property><xsl:text>
</xsl:text>
<qtpRep:Property qtpRep:Name="myidwhencustexists" qtpRep:Hidden="1" qtpRep:ReadOnly="1" qtpRep:Type="STRING">	<qtpRep:Value qtpRep:RegularExpression="0">#32770</qtpRep:Value>	</qtpRep:Property><xsl:text>
</xsl:text>
<qtpRep:Property qtpRep:Name="is owned window" qtpRep:Hidden="0" qtpRep:ReadOnly="0" qtpRep:Type="BOOL">	<qtpRep:Value qtpRep:RegularExpression="0">-1</qtpRep:Value>	</qtpRep:Property><xsl:text>
</xsl:text>
<qtpRep:Property qtpRep:Name="is child window" qtpRep:Hidden="0" qtpRep:ReadOnly="0" qtpRep:Type="BOOL">	<qtpRep:Value qtpRep:RegularExpression="0">0</qtpRep:Value>	</qtpRep:Property><xsl:text>
</xsl:text>
</qtpRep:Properties><xsl:text>
</xsl:text>
<qtpRep:BasicIdentification><xsl:text>
</xsl:text>
<qtpRep:PropertyRef>text</qtpRep:PropertyRef><xsl:text>
</xsl:text>
<qtpRep:PropertyRef>nativeclass</qtpRep:PropertyRef><xsl:text>
</xsl:text>
<qtpRep:PropertyRef>is owned window</qtpRep:PropertyRef><xsl:text>
</xsl:text>
<qtpRep:PropertyRef>is child window</qtpRep:PropertyRef><xsl:text>
</xsl:text>
</qtpRep:BasicIdentification><xsl:text>
</xsl:text>
<qtpRep:LastUpdateTime></qtpRep:LastUpdateTime><xsl:text>
</xsl:text>
<qtpRep:ChildObjects><xsl:text>
</xsl:text>
<qtpRep:Object qtpRep:Class="WinButton" qtpRep:Name="OK"><xsl:text>
</xsl:text>
<qtpRep:Properties><xsl:text>
</xsl:text>
<qtpRep:Property qtpRep:Name="text" qtpRep:Hidden="0" qtpRep:ReadOnly="0" qtpRep:Type="STRING">	<qtpRep:Value qtpRep:RegularExpression="0">OK</qtpRep:Value>	</qtpRep:Property><xsl:text>
</xsl:text>
<qtpRep:Property qtpRep:Name="nativeclass" qtpRep:Hidden="0" qtpRep:ReadOnly="0" qtpRep:Type="STRING">	<qtpRep:Value qtpRep:RegularExpression="0">Button</qtpRep:Value>	</qtpRep:Property><xsl:text>
</xsl:text>
<qtpRep:Property qtpRep:Name="myidwhencustexists" qtpRep:Hidden="1" qtpRep:ReadOnly="1" qtpRep:Type="STRING">	<qtpRep:Value qtpRep:RegularExpression="0">Button</qtpRep:Value>	</qtpRep:Property><xsl:text>
</xsl:text>
</qtpRep:Properties><xsl:text>
</xsl:text>
<qtpRep:BasicIdentification><xsl:text>
</xsl:text>
<qtpRep:PropertyRef>text</qtpRep:PropertyRef><xsl:text>
</xsl:text>
<qtpRep:PropertyRef>nativeclass</qtpRep:PropertyRef><xsl:text>
</xsl:text>
</qtpRep:BasicIdentification><xsl:text>
</xsl:text>
<qtpRep:LastUpdateTime></qtpRep:LastUpdateTime><xsl:text>
</xsl:text>
<qtpRep:ChildObjects><xsl:text>
</xsl:text>
</qtpRep:ChildObjects><xsl:text>
</xsl:text>
</qtpRep:Object><xsl:text>
</xsl:text>
</qtpRep:ChildObjects><xsl:text>
</xsl:text>
</qtpRep:Object><xsl:text>
</xsl:text>
<qtpRep:Object qtpRep:Class="Page" qtpRep:Name="SQO"><xsl:text>
</xsl:text>
<qtpRep:Properties><xsl:text>
</xsl:text>
<qtpRep:Property qtpRep:Name="url" qtpRep:Hidden="1" qtpRep:ReadOnly="1" qtpRep:Type="STRING">	<qtpRep:Value qtpRep:RegularExpression="0">URL</qtpRep:Value>	</qtpRep:Property><xsl:text>
</xsl:text>
<qtpRep:Property qtpRep:Name="title" qtpRep:Hidden="1" qtpRep:ReadOnly="1" qtpRep:Type="STRING">	<qtpRep:Value qtpRep:RegularExpression="0">TITLE</qtpRep:Value></qtpRep:Property><xsl:text>
</xsl:text>
<qtpRep:Property qtpRep:Name="micclass" qtpRep:Hidden="1" qtpRep:ReadOnly="1" qtpRep:Type="STRING">	<qtpRep:Value qtpRep:RegularExpression="0">Page</qtpRep:Value>	</qtpRep:Property><xsl:text>
</xsl:text>
</qtpRep:Properties><xsl:text>
</xsl:text>
<qtpRep:BasicIdentification><xsl:text>
</xsl:text>
<qtpRep:PropertyRef>micclass</qtpRep:PropertyRef><xsl:text>
</xsl:text>
</qtpRep:BasicIdentification><xsl:text>
</xsl:text>
<qtpRep:SmartIdentification qtpRep:Algorithm="Mercury.TolerantPriority" qtpRep:Active="1"><xsl:text>
</xsl:text>
<qtpRep:BaseFilter><xsl:text>
</xsl:text>
<qtpRep:PropertyRef>micclass</qtpRep:PropertyRef><xsl:text>
</xsl:text>
</qtpRep:BaseFilter><xsl:text>
</xsl:text>
<qtpRep:OptionalFilter><xsl:text>
</xsl:text>
<qtpRep:PropertyRef>title</qtpRep:PropertyRef><xsl:text>
</xsl:text>
<qtpRep:PropertyRef>url</qtpRep:PropertyRef><xsl:text>
</xsl:text>
</qtpRep:OptionalFilter><xsl:text>
</xsl:text>
</qtpRep:SmartIdentification><xsl:text>
</xsl:text>
<qtpRep:LastUpdateTime></qtpRep:LastUpdateTime><xsl:text>
</xsl:text>
<qtpRep:ChildObjects><xsl:text>
</xsl:text>
      <xsl:apply-templates />
</qtpRep:ChildObjects><xsl:text>
</xsl:text>
</qtpRep:Object><xsl:text>
</xsl:text>
</qtpRep:ChildObjects><xsl:text>
</xsl:text>
</qtpRep:Object><xsl:text>
</xsl:text>
</qtpRep:Objects><xsl:text>
</xsl:text>
<qtpRep:Parameters></qtpRep:Parameters><xsl:text>
</xsl:text>
<qtpRep:Metadata></qtpRep:Metadata><xsl:text>
</xsl:text>
</qtpRep:ObjectRepository>
</xsl:template>
    
    
    
    <!--page elements templates-->
    <xsl:template match='child::object/child::object'>
    
      <xsl:choose>
        <xsl:when test="count(./prop)=4">
          <xsl:call-template name="mainTemplate">
            <xsl:with-param name="name" select="@name" />
            <xsl:with-param name="value" select="child::prop[@name='value']/child::text()" />
            <xsl:with-param name="index" select="0" />
            <xsl:with-param name="propertyName" select="child::prop[@name='propertyName']/child::text()" />
            <xsl:with-param name="classID" select="child::prop[@name='classID']/child::text()" />
          </xsl:call-template>
        </xsl:when>
        
        
        <xsl:when test="count(./prop)=5">
         <xsl:choose>
           <xsl:when test="string-length(child::prop[@name='index']/child::text())=3">
            <xsl:call-template name="mainTemplate">
              <xsl:with-param name="name" select="@name" />
              <xsl:with-param name="value" select="child::prop[@name='value']/child::text()" />
              <xsl:with-param name="index" select="0" />
              <xsl:with-param name="propertyName" select="child::prop[@name='propertyName']/child::text()" />
              <xsl:with-param name="classID" select="child::prop[@name='classID']/child::text()" />
            </xsl:call-template>
           </xsl:when>
           <xsl:when test="string-length(child::prop[@name='index']/child::text())=5">
            <xsl:variable name="indexText" select="child::prop[@name='index']/child::text()" />
            <xsl:call-template name="mainTemplate">
              <xsl:with-param name="name" select="@name" />
              <xsl:with-param name="value" select="child::prop[@name='value']/child::text()" />
              <xsl:with-param name="index" select="substring($indexText, 5, 1) - 1" />
              <xsl:with-param name="propertyName" select="child::prop[@name='propertyName']/child::text()" />
              <xsl:with-param name="classID" select="child::prop[@name='classID']/child::text()" />
            </xsl:call-template>
           </xsl:when>
          </xsl:choose>
         </xsl:when>
       </xsl:choose>
       
       
       
     </xsl:template>
       
	<xsl:template match='/'>
		<xsl:apply-templates />
	</xsl:template>
	
</xsl:stylesheet>




