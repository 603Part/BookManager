package com.example.book.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.book.model.BookBean;
import com.example.book.model.BorrowBean;
import com.example.book.model.MessageBean;
import com.example.book.model.UserBean;
import com.example.book.model.UserLogin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private static String DB_NAME = "book.db";
    private Context mContext;
    private static SQLiteDatabase manager;
    private static final String TAG     = "DBManager";
    public static void copyDb(Context mContext, String tab_name) {
        InputStream in = null;
        FileOutputStream out = null;

        String path = "/data/data/"+mContext.getPackageName()+"/databases";
        File file = new File(path + "/" +DB_NAME);
        try{
            File file1 = new File(path);
            if (!file1.exists()) {
                file1.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
                in = mContext.getAssets().open(DB_NAME);
                out = new FileOutputStream(file);
                int length = -1;
                byte[] buf = new byte[1024];
                while ((length = in.read(buf)) != -1) {
                    out.write(buf,0,length);
                }
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {

            }
        }
    }


    public static SQLiteDatabase dbManager(Context mContext) {
        String dbPath = "/data/data/" + mContext.getPackageName()
                + "/databases/" + DB_NAME;
        manager = SQLiteDatabase.openOrCreateDatabase(dbPath, null);
        return manager;
    }



    public static UserLogin login(String inputName, String password) {
        UserLogin user = null;
        Cursor cursor = manager.rawQuery("SELECT * FROM UserRoleView WHERE username = '"+inputName+"' AND password = '"+password+"'",null);
        while(cursor.moveToNext()){
            String id = cursor.getString(0);
            String username = cursor.getString(1);
            String pwd = cursor.getString(2);
            String name = cursor.getString(3);
            String gender = cursor.getString(4);
            String phone = cursor.getString(5);
            String role_name = cursor.getString(6);
            user = new UserLogin();
            user.setId(id);
            user.setUsername(username);
            user.setName(name);
            user.setPassword(pwd);
            user.setGender(gender);
            user.setPhone(phone);
            user.setRole_name(role_name);
        }
        cursor.close();
        return user;
    }

    public static int insertNewUser(UserBean user) {
        // 先判断有无此用户
        Cursor cursor = manager.rawQuery("select 1 from user where username = '" + user.getUsername() + "' limit 1;", null);
        boolean b = false; // 默认无
        while (cursor.moveToNext()) {
            b = true; // 有此用户
        }
        if (b) {
            return 1; //用户存在
        }
        try {
            manager.execSQL("INSERT INTO user VALUES(NULL,'"+user.getUsername()+"','"+user.getPassword()+"','"+user.getName()+"','"+user.getGender()+"','"+user.getPhone()+"','"+user.getRole()+"','"+user.getStatus()+"','')");
        } catch (Exception e) {
            e.printStackTrace();
            return 2; // 用户不存在但是插入失败
        }
        cursor.close();
        return 0; // success
    }

    public static void deleteUserByUserName(String username) {
        manager.execSQL("DELETE FROM user where username = '"+username+"'");
    }

    public static void insertBook(BookBean b) {
        manager.execSQL("INSERT INTO book VALUES(NULL,?,?,?,?,?,?,?,?,?,?)", new String[]{
                b.getBookname(), b.getBookAuthor(), b.getIsbn(), b.getPress(), b.getBrief(), b.getPrice(),
                b.getCount(), b.getSurplus(), "", ""
        });
    }

    public static List<BookBean> findAllBook() {
        List<BookBean> bookBeans = new ArrayList<>();
        Cursor cursor = manager.rawQuery("SELECT * FROM book", null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String bookName = cursor.getString(1);
            String bookAuthor = cursor.getString(2);
            String bookIsbn = cursor.getString(3);
            String press = cursor.getString(4);
            String brief = cursor.getString(5);
            String price = cursor.getString(6);
            String count = cursor.getString(7);
            String surplus = cursor.getString(8);
            bookBeans.add(new BookBean(id, bookName, bookAuthor, bookIsbn, press, brief, price, count, surplus, "", ""));
        }
        cursor.close();
        return bookBeans;
    }

    public static void updateBook(BookBean book) {
        manager.execSQL("UPDATE book SET bookname = ?,author = ?,isbn = ?,press = ?,brief = ?,count = ? WHERE bookname = ?"
                ,new String[]{book.getBookname(),book.getBookAuthor(),book.getIsbn(),book.getPress(),book.getBrief(),book.getCount(),book.getBookname()});
    }

    public static void updateBookCount(String id,String sur) {
        manager.execSQL("UPDATE book SET surplus = ? WHERE id = ?"
                ,new String[]{sur,id});
    }

    public static void updateBookCountAndStatus(String id,String sur,String status) {
        manager.execSQL("UPDATE book SET surplus = ?,is_returned = ? WHERE id = ?"
                ,new String[]{sur,status,id});
    }

    public static BookBean findBookByName(String bName) {
        BookBean bookBean = null;
        Cursor cursor = manager.rawQuery("SELECT * FROM book WHERE bookname = '"+bName+"'", null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String bookName = cursor.getString(1);
            String bookAuthor = cursor.getString(2);
            String bookIsbn = cursor.getString(3);
            String press = cursor.getString(4);
            String brief = cursor.getString(5);
            String price = cursor.getString(6);
            String count = cursor.getString(7);
            String surplus = cursor.getString(8);
            bookBean=new BookBean(id, bookName, bookAuthor, bookIsbn, press, brief, price, count, surplus, "", "");
        }
        cursor.close();
        return bookBean;
    }

    public static void insertNotice(String noticeValue) {
        manager.execSQL("INSERT INTO notice VALUES(NULL,?)",new String[]{noticeValue});
    }

    public static List<String> getAllNotice() {
        List<String> mList = new ArrayList<>();
        Cursor cursor = manager.rawQuery("SELECT * FROM notice", null);
        while (cursor.moveToNext()) {
            mList.add(cursor.getString(1));
        }
        cursor.close();
        return mList;
    }

    /**
     * 图书查询
     * @param bName
     * @return
     */
    public static List<BookBean> findBookByLikeName(String bName) {
        List<BookBean> bookBean = new ArrayList<>();

        Cursor cursor = manager.rawQuery("SELECT * FROM book WHERE bookname LIKE '%"+bName+"%'", null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String bookName = cursor.getString(1);
            String bookAuthor = cursor.getString(2);
            String bookIsbn = cursor.getString(3);
            String press = cursor.getString(4);
            String brief = cursor.getString(5);
            String price = cursor.getString(6);
            String count = cursor.getString(7);
            String surplus = cursor.getString(8);
            bookBean.add(new BookBean(id, bookName, bookAuthor, bookIsbn, press, brief, price, count, surplus, "", ""));
        }
        cursor.close();

        return bookBean;
    }


    public static UserLogin findUserByUserName(String username) {
        UserLogin user = null;
        Cursor cursor = manager.rawQuery("SELECT * FROM user WHERE username = '"+username+"'",null);
        while(cursor.moveToNext()){
            String name = cursor.getString(1);
            String pwd = cursor.getString(2);
            String sex = cursor.getString(3);
            String phone = cursor.getString(4);
            String role = cursor.getString(5);
            String nickname = cursor.getString(7);
            String status = cursor.getString(8);
            user = new UserLogin();
            user.setUsername(name);
            user.setPassword(pwd);

        }
        cursor.close();
        return user;
    }

    public static UserLogin findUserById(String uid) {
        UserLogin user = null;
        Cursor cursor = manager.rawQuery("SELECT * FROM UserRoleView WHERE id =" + uid,null);
        while(cursor.moveToNext()){
            String id = cursor.getString(0);
            String username = cursor.getString(1);
            String pwd = cursor.getString(2);
            String name = cursor.getString(3);
            String gender = cursor.getString(4);
            String phone = cursor.getString(5);
            String role_name = cursor.getString(6);
            user = new UserLogin();
            user.setId(id);
            user.setUsername(username);
            user.setName(name);
            user.setPassword(pwd);
            user.setGender(gender);
            user.setPhone(phone);
            user.setRole_name(role_name);
        }
        cursor.close();
        return user;
    }

    public static void updateUser(UserBean userBean) {
        String sql = "UPDATE user SET username = '" + userBean.getUsername() + "'" +
                ",name = '" + userBean.getName() + "',gender = '" + userBean.getGender() + "'," +
                "phone='" + userBean.getPhone() + "',password = '" + userBean.getPassword() + "' WHERE id = '" + userBean.getId()+"'";
        Log.d(TAG, "updateUser: " + sql);
        try {
            manager.execSQL(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(UserBean userBean) {
        manager.execSQL("UPDATE user SET username = '"+userBean.getUsername()+"'" +
                ",name = '"+userBean.getName()+"',gender = '"+userBean.getGender()+"'," +
                "phone='"+userBean.getPhone()+"',password = '"+userBean.getPassword()+"', status = '"+userBean.getStatus()+"' WHERE id = '" + userBean.getId()+"'");
    }

    public static void deleteUserById(String id) {
        manager.execSQL("DELETE FROM user WHERE id = " + id);
    }

    public static List<UserBean> getAllUser() {
        List<UserBean> mList = new ArrayList<>();
        UserBean user = null;
        Cursor cursor = manager.rawQuery("SELECT * FROM user",null);
        while(cursor.moveToNext()){
            String id = cursor.getString(0);
            String username = cursor.getString(1);
            String password = cursor.getString(2);
            String name = cursor.getString(3);
            String gender = cursor.getString(4);
            String phone = cursor.getString(5);
            String role = cursor.getString(6);
            String status = cursor.getString(7);

            user = new UserBean(id,username,password,name,gender,phone,role,status);

            mList.add(user);
        }
        cursor.close();
        return mList;
    }

    public static List<MessageBean> getAllMessage() {
        List<MessageBean> messageBeans = new ArrayList<>();
        Cursor cursor = manager.rawQuery("SELECT * FROM message", null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String message = cursor.getString(1);
            String userId = cursor.getString(2);
            String username = cursor.getString(3);
            String reply = cursor.getString(4);
            String status = cursor.getString(5);

            MessageBean messageBean = new MessageBean();
            messageBean.setId(id);

            messageBean.setMessage(message);
            messageBean.setUser_id(userId);
            messageBean.setUsername(username);
            messageBean.setReply(reply);
            messageBean.setStatus(status);
            messageBeans.add(messageBean);
        }

        cursor.close();

        return messageBeans;
    }

    public static void insertMessage(MessageBean messageBean) {
        manager.execSQL("INSERT INTO message VALUES(NULL,?,?,?,?,?,?)",new String[]{
                messageBean.getMessage(),messageBean.getUser_id(),messageBean.getUsername(),
                messageBean.getReply(),messageBean.getStatus(),""
        });
    }

    public static void updateMessage(String id,String reply) {
        manager.execSQL("UPDATE message SET reply = ?,status = '1' WHERE id = ?",new String[]{
                reply,id
        });
    }

    public static List<MessageBean> getMessageByUser(String userame) {
        List<MessageBean> messageBeans = new ArrayList<>();
        Cursor cursor = manager.rawQuery("SELECT * FROM message WHERE user_id = ?", new String[]{userame});
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String message = cursor.getString(1);
            String userId = cursor.getString(2);
            String username = cursor.getString(3);
            String reply = cursor.getString(4);
            String status = cursor.getString(5);
            MessageBean messageBean = new MessageBean();
            messageBean.setId(id);
            messageBean.setMessage(message);
            messageBean.setUser_id(userId);
            messageBean.setUsername(username);
            messageBean.setReply(reply);
            messageBean.setStatus(status);
            messageBeans.add(messageBean);
        }

        cursor.close();

        return messageBeans;
    }

    public static void insertBrow(String bookId,String username,String name) {
        manager.execSQL("INSERT INTO borrow VALUES(NULL,?,?,?,?)",new String[]{
                bookId,username,name,"0"
        });
    }

    public static void updateBrow(String id) {
        manager.execSQL("UPDATE borrow SET is_returned = ? WHERE id = ?",new String[]{"1",id});
    }

    public static List<BookBean> getAllBorrowBook(String userId) {
        List<BookBean> bookBeans = new ArrayList<>();
        Cursor cursor = manager.rawQuery("SELECT * FROM BookView WHERE userId = ?", new String[]{userId});
        while (cursor.moveToNext()) {
            BookBean bookBean = new BookBean();
            bookBean.setRemark(cursor.getString(0));
            bookBean.setId(cursor.getString(1));
            bookBean.setBookAuthor(cursor.getString(3));
            bookBean.setIsbn(cursor.getString(4));
            bookBean.setBookname(cursor.getString(5));
            bookBean.setPress(cursor.getString(6));
            bookBean.setCount(cursor.getString(9));
            bookBean.setSurplus(cursor.getString(10));
            bookBeans.add(bookBean);
        }
        return bookBeans;
    }
}
