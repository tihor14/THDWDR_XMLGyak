<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    <html>
      <head>
        <style>
          table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 20px;
          }

          th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
          }

          th {
            background-color: #f2f2f2;
          }
        </style>
      </head>
      <body>
        <h2>Kurzusok táblázatos formában</h2>
        <table>
          <tr>
            <th>ID</th>
            <th>Kurzusnév</th>
            <th>Kredit</th>
            <th>Hely</th>
            <th>Időpont</th>
            <th>Oktató</th>
            <th>Óraadó</th>
          </tr>
          <xsl:for-each select="THDWDR_kurzusfelvetel/kurzusok/kurzus">
            <tr>
              <td><xsl:value-of select="@id"/></td>
              <td><xsl:value-of select="kurzusnev"/></td>
              <td><xsl:value-of select="kredit"/></td>
              <td><xsl:value-of select="hely"/></td>
              <td><xsl:value-of select="idopont"/></td>
              <td><xsl:value-of select="oktato"/></td>
              <td><xsl:value-of select="oraado"/></td>
            </tr>
          </xsl:for-each>
        </table>
      </body>
    </html>
  </xsl:template>

</xsl:stylesheet>
