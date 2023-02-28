import org.apache.spark.sql.functions._
import org.apache.spark.sql.expressions.Window


val inputData = Seq( ("2018-01-01 11:00:00", "u1"), ("2018-01-01 12:10:00", "u1"), ("2018-01-01 13:00:00", "u1"), ("2018-01-01 13:30:00", "u1"), ("2018-01-01 14:40:00", "u1"), ("2018-01-01 15:30:00", "q1"), ("2018-01-01 16:20:00", "u1"), ("2018-01-01 16:50:00", "u1"), ("2018-01-01 11:00:00", "u2"), ("2018-01-02 11:00:00", "u2") 
).toDF("click_time", "user_id") 
inputData.show




val windowSpec = Window.partitionBy($"user_id").orderBy($"click_time")
 val df1 = inputData.withColumn("lagvalue" , lag($"click_time" , 1).over(windowSpec))
 val df2 = df1.withColumn("tsDiff" , ((unix_timestamp($"click_time")-unix_timestamp($"lagvalue"))/60))
 val df3 = df2.withColumn("tsDiff", when($"tsDiff".isNull, 0).otherwise($"tsDiff")) 
val df4 = df3.withColumn("SessionNew" , when($"tsDiff" >30 , 1).otherwise(0))
 val df5 = df4.withColumn("Session_Name" , concat($"user_id",lit("--S") , sum($"SessionNew").over(windowSpec))) 
df5.orderBy($"Session_Name").write.csv("/root/output")
