<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>User List</title>
  <style>
  @import url('https://fonts.googleapis.com/css?family=Montserrat:400,500,600,700');
body{
  margin: 0;
  padding: 0;
  font-family: 'Montserrat', sans-serif;
}
a{
  transition: all .5s;
  text-decoration: none;
  color: #fff;

}
img{
  width: 100%;
  transition: all .5s;
}
section{
  padding: 70px 0;
  margin-top: 50px;
  background: rgba(116, 185, 255,0.09);
  text-align: center;
}
.container{
  max-width: 1200px;
  width: 100%;
  margin: 0 auto;
}
main{
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  grid-gap: 30px;
  margin: 30px 15px 0px 15px;
}
header{
  width: 70%;
  margin: 0 auto;
}
header h1{
  margin: 0;
  font-size: 50px;
  color: rgba(52, 73, 94,1.0);
  font-weight: 500;
}
.singleBlog{
  position: relative;
  overflow: hidden;
  max-height: 580px;
  height: 100%;
  box-sizing: border-box;
  box-shadow: 0 2px 10px #ccc;
  transition: all .5s;
}
.singleBlog:hover{
  box-shadow: 0 2px 13px #ccc;
  transition: all .5s;
}
.singleBlog:hover img{
  transform: scale(1.1);
  transition: all .4s;
}
.blogContent{
  position: absolute;
  left: 0;
  bottom: -45px;
  width: 100%;
  color: #fff;
  padding: 10px 5px;
  background: linear-gradient(45deg, rgba(21,82,153,1) 0%,rgba(144,192,229,.4) 100%);
  transition: all .5s;
}
.singleBlog:hover .blogContent{
  bottom: 0;
  transition: all .5s;
}

.blogContent h3{
  font-size: 20px;
  font-weight: 500;
  margin: 0;
}
.blogContent h3 span{
  display: block;
  font-size: 60%;
  margin-top: 5px;
  font-weight: 600;
  color: rgba(52, 73, 94,1.0)
}

.blogContent a{
  font-size: 14px;
  font-weight: 500;
}
.blogContent .btn{
  display: inline-block;
  padding: 5px 10px;
  border: 1px solid #fff;
  border-radius: 3px;
  font-weight: 400;
}
.blogContent .btn:hover{
  background: rgba(52, 152, 219,1.0);
  transition: all .5s;
  text-decoration: none;
}
.blogContent a:hover{
  text-decoration: underline;
}
@media (max-width:992px) {
  main{
    grid-template-columns: repeat(3, 1fr);
  grid-gap: 20px;
  }
}
@media (max-width:767px) {
  main{
    grid-template-columns: repeat(2, 1fr);
  grid-gap: 15px;
  }
  header{
    width: 95%;
  }
  .blogContent{
    bottom: 0;
  }
}
@media (max-width:500px) {

    main{
      grid-template-columns: repeat(1, 1fr);
    }
}

  </style>
</head>
<body>
  <section>
    <div class="container">
      <header>
        <h1>USER DATA</h1>
      </header>
        <main>
	    <?php
                 include './Agro.php';
               $users=new Users();
               $fc=$users->gets();
                   foreach ($fc as $key => $value){
                     foreach($value as $key2 => $val){
                        foreach($val as $k => $v){
                           echo "<div class='singleBlog'>";
                           echo"<img src='".$v['image']."' alt=''>";
                           echo"<div class='blogContent'>";
                           echo"<h3>".$v['cropname']."</h3>";
                           echo"<p>'".$v['username']." | ".$v['date']."</p>";
                           echo"<form action='detail.php' method='post'>";
                           echo"<input type='text' name='name' value='".$key."'
                           style='display:none;'>";
                           echo "<input type='submit' value='Read More' class='btn btn-primary'></form>";
                           echo "</div>
                           <div>";break;}}}?>
         </main>
	  </div>
	</section>

	</body>
     </html>