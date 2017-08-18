<?xml version="1.0" encoding="euc-kr" ?> 
   
  <xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"> 
    <xsl:output method = "html" indent="yes" encoding="euc-kr" /> 
   
     <xsl:template match="Addlist"> 
    <html> 
    <head><title>주소록</title></head> 
    <body> 
  
      현재 등록되어 있는 주소록 은 다음과 같습니다. 
        
       <ul> 
      <xsl:for-each select="Address"> 
      <br/>
        <li><b><xsl:value-of select="Name" /></b> 
           [ CellPhone :<xsl:value-of select="cell-Phone" />]
            <br /> 
            <i><xsl:value-of select="Addr" /></i> <br/>
            <i><xsl:value-of select="Tel"/></i>
            <br />
        </li> 
      </xsl:for-each> 
      </ul> 

   
         </body> 
    </html> 
    
    </xsl:template> 
  </xsl:stylesheet> 
