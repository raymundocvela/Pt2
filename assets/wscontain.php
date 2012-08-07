<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <script src="http://maps.googleapis.com/maps/api/js?libraries=geometry&sensor=true" type="text/javascript"></script>
</head>
<body>
    <?php
        include('conectar.php');
        $usr=$_REQUEST['usr'];
        $inOut="";
        echo $usr;
        //obtener id usr
        $qUsr="SELECT idusuarios FROM usuarios WHERE usuario='$usr'";
        echo "<br>".$qUsr;
        $result=mysql_query($qUsr);
        $idUsr=mysql_fetch_array($result);
        //obtenemos restricción
        $query="SELECT restriccion FROM usuarios WHERE idusuarios ='".$idUsr[0]."'";
        echo "<br>".$query;
        $result=mysql_query($query);
        $row=mysql_fetch_array($result);
        if($row[0]!=NULL){
            //Si hay restriccion de área
            $js=$row[0];
            echo "<br>restricción: ".$js."fin";
            $laty=$_REQUEST['laty'];
            $lonx=$_REQUEST['lonx'];
            //Crea el poligono
            echo '
            <script type="text/javascript">
            var inOut="out";
            var punto= new google.maps.LatLng('.$laty.','.$lonx.');
            '.$js.';
            var polyRest = new google.maps.Polygon(polyOptions);
            if (google.maps.geometry.poly.containsLocation(punto,polyRest))
            inOut="in";
            else inOut="out"
            </script>';
            echo "<br>usr:".$_REQUEST['usr'];
            echo "<br>laty:".$_REQUEST['laty'];
            echo "<br>lonx:".$_REQUEST['lonx']."<br>";
            //Se pasa el valor de javascript
            $inOut="<script>document.write(inOut)</script>";
        } 
        else "no hay restricción de área";
        echo "<br>inout var PHP?:".$inOut."-";
        mysql_close();
    ?>
    </body>
    