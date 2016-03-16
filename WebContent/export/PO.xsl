<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    <html>
    <head>
      <title>Placed Order</title>
    </head>
    <body>
	<xsl:apply-templates/>
    </body>
    </html>
    </xsl:template>

    <xsl:template match="order">
	<h1>MiniMart Receipt</h1>
	<h2>Thank you for shopping at MiniMart!</h2>
	<p>Receipt Hash: <xsl:value-of select="@hash"/></p>
	<p>Submitted: <xsl:value-of select="@submitted"/></p>
	<xsl:apply-templates select="./customer"/>
	<p><b>Total: $<xsl:value-of select="./total"/></b></p>
	<p><b>HST: $<xsl:value-of select="./HST"/></b></p>
	<p><b>Shipping: $<xsl:value-of select="./shipping"/></b></p>
	<p><b>Grand Total: $<xsl:value-of select="./grandTotal"/></b></p>
	<ol>
	<xsl:apply-templates select="./item"/>
	</ol>
    </xsl:template>
    
    <xsl:template match="customer">
    	<p>Customer #: <xsl:value-of select="@account"/></p>
    	<p>Customer Name: <xsl:value-of select="./name"/></p>    	
    </xsl:template>
    
    <xsl:template match="item">
	<li>
	<ul>
	  <li>Item #: #<xsl:value-of select="@number"/></li>
	  <li>Item Name: <xsl:value-of select="./name"/></li>
	  <li>Quantity: <xsl:value-of select="./quantity"/></li>
	  <li>Item Price: $<xsl:value-of select="./price"/></li>
	  <li>Item Extended Price: $<xsl:value-of select="./extended"/></li>
	  <br />
	  </ul>
	</li>
    </xsl:template>

</xsl:stylesheet>
