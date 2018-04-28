<?php  
  /** 
  * n张朴克牌随机分成4组 
  * 
  * @param int $n n张朴克牌 
  * @return array 
  * 
  */  
  function groupby($n)  
  {  
      $a = $b = $c = $d = array();  
      $arr = array();         // n张有序的朴克牌  
      $arr_about = array();   // n张无序的朴克牌  
      for ($i=1; $i < $n+1; $i++) {   
          $arr[] = $i;    // 把朴克牌赋值给一个空的数组  
      }  

      $dw_num = $n-1;     // 下标  
      while(!empty($arr)){  

          $rand_number = mt_rand(0,$dw_num);  // 随机下标  
          $arr_about[] = $arr[$rand_number];  // 把随机下标对应的值付给无序的数组  
          unset($arr[$rand_number]);          // 清空掉这个随机下标对应的值  
          $arr = array_values($arr);          // 去掉下标  
          $dw_num--;                          // 下标上限降低  
      }  
      $per_num = count($arr_about)/4;  
      $a = array_slice($arr_about,0,$per_num);  
      $b = array_slice($arr_about,$per_num,$per_num);  
      $c = array_slice($arr_about,2*$per_num,$per_num);  
      $d = array_slice($arr_about,3*$per_num,$per_num);  
      return array($a,$b,$c,$d);  
  }  
  print_r(groupby(52));  
?>
