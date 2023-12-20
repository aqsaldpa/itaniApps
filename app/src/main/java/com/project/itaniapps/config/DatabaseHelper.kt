package com.project.itaniapps.config

import android.R.attr.value
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.project.itaniapps.model.News
import com.project.itaniapps.model.Product
import com.project.itaniapps.model.User


class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null,
    DATABASE_VERSION
) {
    private val CREATE_TABLE_USER = "CREATE TABLE $TABLE_USER (" +
            "$COLUMN_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "$COLUMN_USER_NAME TEXT, " +
            "$COLUMN_USER_EMAIL TEXT," +
            "$COLUMN_USER_ADDRESS TEXT," +
            "$COLUMN_USER_PHONE_NUMBER TEXT, " +
            "$COLUMN_USER_PASSWORD TEXT)"

    private val CREATE_TABLE_NEWS = "CREATE TABLE $TABLE_NEWS (" +
            "$COLUMN_ID_NEWS INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "$COLUMN_IMAGE_URL_NEWS TEXT, " +
            "$COLUMN_TYPE_NEWS TEXT," +
            "$COLUMN_TITLE_NEWS TEXT," +
            "$COLUMN_DESC_NEWS TEXT," +
            "$COLUMN_DATE_NEWS TEXT) "


    private val CREATE_TABLE_PRODUCT = "CREATE TABLE $TABLE_PRODUCT (" +
            "$COLUMN_ID_PRODUCT INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "$COLUMN_IMAGE_URL_PRODUCT TEXT, " +
            "$COLUMN_NAME_PRODUCT TEXT, " +
            "$COLUMN_STOCK_PRODUCT INTEGER," +
            "$COLUMN_PRICE_PRODUCT INTEGER)"

    private val DROP_TABLE_USER = "DROP TABLE IF EXISTS $TABLE_USER"

    private val DROP_TABLE_NEWS = "DROP TABLE IF EXISTS $TABLE_NEWS"

    private val DROP_TABLE_PRODUCT = "DROP TABLE IF EXISTS $TABLE_PRODUCT"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_USER)
        db?.execSQL(CREATE_TABLE_NEWS)
        db?.execSQL(CREATE_TABLE_PRODUCT)
        db?.isOpen()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DROP_TABLE_USER)
        db?.execSQL(DROP_TABLE_NEWS)
        db?.execSQL(DROP_TABLE_PRODUCT)
    }


    fun registerUser(user: User) {
        val db = this.writableDatabase
        val value = ContentValues()
        value.put(COLUMN_USER_NAME, user.username)
        value.put(COLUMN_USER_EMAIL, user.email)
        value.put(COLUMN_USER_ADDRESS, user.address)
        value.put(COLUMN_USER_PHONE_NUMBER, user.number)
        value.put(COLUMN_USER_PASSWORD, user.password)

        db.insert(TABLE_USER, null, value)
    }

    fun submitNews(news: News) {
        val db = this.writableDatabase
        val value = ContentValues()
        value.put(COLUMN_IMAGE_URL_NEWS, news.image)
        value.put(COLUMN_TYPE_NEWS, news.type)
        value.put(COLUMN_TITLE_NEWS, news.title)
        value.put(COLUMN_DESC_NEWS, news.desc)
        value.put(COLUMN_DATE_NEWS, news.date)

        db.insert(TABLE_NEWS, null, value)
    }

    fun submitProduct(product: Product) {
        val db = this.writableDatabase
        val value = ContentValues()
        value.put(COLUMN_IMAGE_URL_PRODUCT, product.image)
        value.put(COLUMN_NAME_PRODUCT, product.name)
        value.put(COLUMN_STOCK_PRODUCT, product.stock)
        value.put(COLUMN_PRICE_PRODUCT, product.price)

        db.insert(TABLE_PRODUCT, null, value)
    }

    fun updateNews(news: News) {
        val db = this.writableDatabase
        val value = ContentValues()
        value.put(COLUMN_IMAGE_URL_NEWS, news.image)
        value.put(COLUMN_TYPE_NEWS, news.type)
        value.put(COLUMN_TITLE_NEWS, news.title)
        value.put(COLUMN_DESC_NEWS, news.desc)
        value.put(COLUMN_DATE_NEWS, news.date)
        db.update(
            TABLE_NEWS, value, COLUMN_ID_NEWS + "=?", arrayOf(
                news.id.toString()
            )
        )
    }

    fun updateProduct(product: Product) {
        val db = this.writableDatabase
        val value = ContentValues()
        value.put(COLUMN_IMAGE_URL_PRODUCT, product.image)
        value.put(COLUMN_NAME_PRODUCT, product.name)
        value.put(COLUMN_STOCK_PRODUCT, product.stock)
        value.put(COLUMN_PRICE_PRODUCT, product.price)

        db.update(
            TABLE_PRODUCT, value, COLUMN_ID_PRODUCT + "=?", arrayOf(
                product.id.toString()
            )
        )
    }

    fun checkUserLogin(email: String, password: String): Boolean {
        val columns = arrayOf(
            COLUMN_USER_EMAIL,
            COLUMN_USER_PASSWORD
        )
        val db = this.readableDatabase

        val selection = "$COLUMN_USER_EMAIL = ? AND $COLUMN_USER_PASSWORD = ?"

        val selectionArgs = arrayOf(email, password)

        val cursor: Cursor = db.query(
            TABLE_USER,
            columns,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        val cursorCount: Int = cursor.count
        cursor.close()
        db.close()
        return cursorCount > 0
    }

    @SuppressLint("Range")
    fun getUser(email: String): User {
        val db = this.readableDatabase

        val selectionArgs = arrayOf(email)
        val cursor = db.rawQuery(
            "SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_USER_EMAIL + " = " + "?",
            selectionArgs
        )

        val user = User()

        while (cursor.moveToNext()) {
            user.username = cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)).toString()
            user.email = cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)).toString()
            user.address = cursor.getString(cursor.getColumnIndex(COLUMN_USER_ADDRESS)).toString()
            user.number =
                cursor.getString(cursor.getColumnIndex(COLUMN_USER_PHONE_NUMBER)).toString()
        }
        cursor.close()
        db.close()
        return user
    }

    @SuppressLint("Range")
    fun getNewsById(id: String): News {
        val db = this.readableDatabase

        val selectionArgs = arrayOf(id)
        val cursor = db.rawQuery(
            "SELECT * FROM " + TABLE_NEWS + " WHERE " + COLUMN_ID_NEWS + " = " + "?",
            selectionArgs
        )

        val news = News()

        while (cursor.moveToNext()) {
            news.id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_NEWS))
            news.image = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_URL_NEWS)).toString()
            news.date = cursor.getString(cursor.getColumnIndex(COLUMN_DATE_NEWS)).toString()
            news.title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE_NEWS)).toString()
            news.desc = cursor.getString(cursor.getColumnIndex(COLUMN_DESC_NEWS)).toString()
            news.type = cursor.getString(cursor.getColumnIndex(COLUMN_TYPE_NEWS)).toString()
        }
        cursor.close()
        db.close()
        return news
    }

    @SuppressLint("Range")
    fun deleteNewsbyId(id: String): ArrayList<News> {
        val db = this.readableDatabase

        val selectionArgs = arrayOf(id)
        val cursor = db.rawQuery(
            "DELETE FROM " + TABLE_NEWS + " WHERE " + COLUMN_ID_NEWS + " = " + "?",
            selectionArgs
        )
        val newsList = ArrayList<News>()
        while (cursor.moveToNext()) {
            getAllNews()
            val news = News(
                cursor.getString(0).toInt(),
                cursor.getString(1).toString(),
                cursor.getString(2).toString(),
                cursor.getString(3).toString(),
                cursor.getString(4).toString(),
                cursor.getString(5).toString()
            )
            news.id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_NEWS))
            news.image = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_URL_NEWS)).toString()
            news.date = cursor.getString(cursor.getColumnIndex(COLUMN_DATE_NEWS)).toString()
            news.title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE_NEWS)).toString()
            news.desc = cursor.getString(cursor.getColumnIndex(COLUMN_DESC_NEWS)).toString()
            news.type = cursor.getString(cursor.getColumnIndex(COLUMN_TYPE_NEWS)).toString()

            newsList.add(news)
        }

        cursor.close()
        db.close()
        return newsList
    }

    @SuppressLint("Range")
    fun getProductById(id: String): Product {
        val db = this.readableDatabase

        val selectionArgs = arrayOf(id)
        val cursor = db.rawQuery(
            "SELECT * FROM " + TABLE_PRODUCT + " WHERE " + COLUMN_ID_PRODUCT + " = " + "?",
            selectionArgs
        )

        val product = Product()

        while (cursor.moveToNext()) {
            product.id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_PRODUCT))
            product.image = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_URL_PRODUCT)).toString()
            product.name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_PRODUCT)).toString()
            product.stock = cursor.getInt(cursor.getColumnIndex(COLUMN_STOCK_PRODUCT))
            product.price = cursor.getInt(cursor.getColumnIndex(COLUMN_PRICE_PRODUCT))
        }
        cursor.close()
        db.close()
        return product
    }

    @SuppressLint("Range")
    fun deleteProductbyId(id: String): ArrayList<Product> {
        val db = this.readableDatabase

        val selectionArgs = arrayOf(id)
        val cursor = db.rawQuery(
            "DELETE FROM " + TABLE_PRODUCT + " WHERE " + COLUMN_ID_PRODUCT + " = " + "?",
            selectionArgs
        )
        val productList = ArrayList<Product>()
        while (cursor.moveToNext()) {
            getAllProduct()
            val product = Product(
                cursor.getString(0).toInt(),
                cursor.getString(1).toString(),
                cursor.getString(2).toString(),
                cursor.getInt(3),
                cursor.getInt(4),
            )
            product.id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_PRODUCT))
            product.image = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_URL_PRODUCT)).toString()
            product.name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_PRODUCT)).toString()
            product.stock = cursor.getInt(cursor.getColumnIndex(COLUMN_STOCK_PRODUCT))
            product.price = cursor.getInt(cursor.getColumnIndex(COLUMN_PRICE_PRODUCT))

            productList.add(product)
        }

        cursor.close()
        db.close()
        return productList
    }

    @SuppressLint("Range")
    fun getAllProduct(): ArrayList<Product> {
        val db = this.readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM " + TABLE_PRODUCT,
            null
        )

        val productList = ArrayList<Product>()


        while (cursor.moveToNext()) {
            val product = Product(
                cursor.getString(0).toInt(),
                cursor.getString(1).toString(),
                cursor.getString(2).toString(),
                cursor.getInt(3),
                cursor.getInt(4),
            )
            product.id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_PRODUCT))
            product.image = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_URL_PRODUCT)).toString()
            product.name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_PRODUCT)).toString()
            product.stock = cursor.getInt(cursor.getColumnIndex(COLUMN_STOCK_PRODUCT))
            product.price = cursor.getInt(cursor.getColumnIndex(COLUMN_PRICE_PRODUCT))

            productList.add(product)

        }
        cursor.close()
        db.close()
        return productList
    }

    @SuppressLint("Range")
    fun getAllNews(): ArrayList<News> {
        val db = this.readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM " + TABLE_NEWS,
            null
        )

        val newsList = ArrayList<News>()


        while (cursor.moveToNext()) {
            val news = News(
                cursor.getString(0).toInt(),
                cursor.getString(1).toString(),
                cursor.getString(2).toString(),
                cursor.getString(3).toString(),
                cursor.getString(4).toString(),
                cursor.getString(5).toString()
            )
            news.id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_NEWS))
            news.image = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_URL_NEWS)).toString()
            news.date = cursor.getString(cursor.getColumnIndex(COLUMN_DATE_NEWS)).toString()
            news.title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE_NEWS)).toString()
            news.desc = cursor.getString(cursor.getColumnIndex(COLUMN_DESC_NEWS)).toString()
            news.type = cursor.getString(cursor.getColumnIndex(COLUMN_TYPE_NEWS)).toString()

            newsList.add(news)

        }
        cursor.close()
        db.close()
        return newsList
    }

    fun checkUser(username: String): Boolean {
        val columns = arrayOf(
            COLUMN_USER_NAME
        )
        val db = this.readableDatabase

        val selection = "$COLUMN_USER_NAME = ?"

        val selectionArgs = arrayOf(username)

        val cursor: Cursor = db.query(
            TABLE_USER,
            columns,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        val cursorCount: Int = cursor.count
        cursor.close()
        db.close()
        return cursorCount > 0
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "itani.db"
        const val TABLE_USER = "tbl_user"
        const val TABLE_NEWS = "tbl_news"
        const val TABLE_PRODUCT = "tbl_product"

        const val COLUMN_ID_NEWS = "news_id"
        const val COLUMN_IMAGE_URL_NEWS = "news_image_url"
        const val COLUMN_TYPE_NEWS = "news_type"
        const val COLUMN_TITLE_NEWS = "news_title"
        const val COLUMN_DESC_NEWS = "news_desc"
        const val COLUMN_DATE_NEWS = "news_date"

        const val COLUMN_ID_PRODUCT = "product_id"
        const val COLUMN_IMAGE_URL_PRODUCT = "product_image_url"
        const val COLUMN_NAME_PRODUCT = "product_name"
        const val COLUMN_STOCK_PRODUCT = "product_stock"
        const val COLUMN_PRICE_PRODUCT = "product_price"

        const val COLUMN_USER_ID = "user_id"
        const val COLUMN_USER_NAME = "user_name"
        const val COLUMN_USER_PASSWORD = "user_password"
        const val COLUMN_USER_EMAIL = "user_email"
        const val COLUMN_USER_ADDRESS = "user_adress"
        const val COLUMN_USER_PHONE_NUMBER = "user_phone_number"
    }
}