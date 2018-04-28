<?php  
  /** 
  * n pokers split into 4 groups randomly 
  * 
  * @param int $n n pokers 
  * @return array 
  * 
  */  
  function groupby($n)  
  {  
      $a = $b = $c = $d = array();  
      $arr = array();         // n pokers in order  
      $arr_about = array();   // n pokers in disorder 
      for ($i=1; $i < $n+1; $i++) {   
          $arr[] = $i;    // assign poker to an empty array 
      }  

      $dw_num = $n-1;     // indexing  
      while(!empty($arr)){  

          $rand_number = mt_rand(0, $dw_num);  // random indexing
          $arr_about[] = $arr[$rand_number];  // assign value of the random index to the disordered array 
          unset($arr[$rand_number]);          // clear values  
          $arr = array_values($arr);          // remove index  
          $dw_num--;
      }  
      $per_num = count($arr_about)/4;  
      $a = array_slice($arr_about, 0, $per_num);  
      $b = array_slice($arr_about, $per_num, $per_num);
      $c = array_slice($arr_about, 2*$per_num, $per_num);  
      $d = array_slice($arr_about, 3*$per_num, $per_num);  
      return array($a, $b, $c, $d);  
  }  
  print_r(groupby(52));  
?>
