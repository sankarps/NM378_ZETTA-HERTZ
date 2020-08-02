<?php

require_once "./vendor/autoload.php";

use Kreait\Firebase\Factory;
use Kreait\Firebase\ServiceAccount;


class Users{
        public $database;
        public $dbname='Users';


        public function __construct(){
            $acc= ServiceAccount::fromJsonFile(__DIR__.'/secret/sample-52f87-f9397c330461.json');
            $firebase=(new Factory)->withServiceAccount($acc)->createDatabase();
            $this->database=$firebase->getReference();
        }
        public function next($data){
            return $this->database->getChild($data)->getValue();
        }
        public function before($data){
            return $this->database->getChild($data)->getKey();
        }
        public function gets(){
            $f=self::before($this->dbname);
           
            $fb=$this->database->getChild($f)->getKey();
            $fc=$this->database->getChild($fb)->getValue();#user array
            return $fc;
        
          /* foreach ($fc as $key => $value) {
               echo $key."\n";
               $i=0;
                foreach ($value as $key2 => $val) {
                    $hi=asort($val);#to make the array reverse
                    echo gettype($hi);
                    foreach ($val as $abs => $vbs) { 
                        echo "\n".$abs."hi\n";
                        break;
                    }
                        /*foreach ($val as $k => $v) {
                            echo $v['cropage']."\n";
                        }*/
                        #echo $val['cropage'];
                    
                   /* foreach ($val as $k => $v) {
                   
                  /*  foreach ($val as $k => $v) {
                     echo  "<h3>".$v['cropage']."</h3>";
                        echo "<img src='".$v['imgurl']."' >";
                       echo  "<p>".$v['cropname']."</p>";
                       /* foreach ($v as $k2 => $v2) {
                            echo $k2." : ".$v2."\n";
                        }
                   }}
                }
            }*/
        }
      
    }



?>