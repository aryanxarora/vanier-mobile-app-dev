package ca.vanier.courseproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import ca.vanier.courseproject.Course;

public class CourseBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABSE_NAME = "courseBase.db";

    public CourseBaseHelper(Context context){
        super (context, DATABSE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + CourseDbSchema.CourseTable.NAME
                + "(" +
                CourseDbSchema.CourseTable.Cols.COURSE_NO + ", " +
                CourseDbSchema.CourseTable.Cols.COURSE_NAME + ", " +
                CourseDbSchema.CourseTable.Cols.MAX_ENRL + ", " +
                CourseDbSchema.CourseTable.Cols.CREDITS + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){

    }

    private static ContentValues getContentValues(Course course){
        ContentValues values = new ContentValues();
        values.put(CourseDbSchema.CourseTable.Cols.COURSE_NO, course.getCourse_no());
        values.put(CourseDbSchema.CourseTable.Cols.COURSE_NAME, course.getCourse_name());
        values.put(CourseDbSchema.CourseTable.Cols.MAX_ENRL, course.getMax_enrl());
        values.put(CourseDbSchema.CourseTable.Cols.CREDITS, 3);
        return values;
    }

    public void addNewCourse(Course course)
    {
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues values = getContentValues(course);
        db.insert(CourseDbSchema.CourseTable.NAME, null, values);
        db.close();
    }

    public ArrayList<Course> readCourse()
    {
        SQLiteDatabase db =  this.getReadableDatabase();
        Cursor cursorCourse = db.rawQuery("SELECT * FROM " + CourseDbSchema.CourseTable.NAME, null);
        ArrayList<Course> courseModelArrayList = new ArrayList<>();
        if(cursorCourse.moveToFirst())
        {
            do{
                courseModelArrayList.add(new Course (cursorCourse.getString(0),
                        cursorCourse.getString(1), cursorCourse.getInt(2)));
            }
            while (cursorCourse.moveToNext());
        }
        cursorCourse.close();

        return courseModelArrayList;
    }

    public void updateCourse (Course course)
    {
        String course_noString = course.getCourse_no();
        ContentValues values = getContentValues(course);
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(CourseDbSchema.CourseTable.NAME, values, CourseDbSchema.CourseTable.Cols.COURSE_NO + "=?",
                new String[] {course_noString});
    }

    public void deleteCourse (Course course)
    {
        String course_noString = course.getCourse_no();
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(CourseDbSchema.CourseTable.NAME, CourseDbSchema.CourseTable.Cols.COURSE_NO + "=?",
                new String[] {course_noString});
    }

}
