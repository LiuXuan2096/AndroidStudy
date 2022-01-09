package com.liuxuan2096.databindingdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.liuxuan2096.databindingdemo.databinding.ActivityMainBinding;
import com.liuxuan2096.databindingdemo.model.Book;

/**
 * 演示简单的TextView绑定
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_main);

        Book book = new Book("Android高性能编程", "叶坤");
        book.rating = 5;
        // 将对象传递到layout中
        activityMainBinding.setBook(book);
        activityMainBinding.setEventHandler(new EventHandleListener(this));
    }

    public class EventHandleListener {
        protected Context context;

        public EventHandleListener(Context context) {
            this.context = context;
        }

        public void onButtonClicked(View view) {
            Toast.makeText(context, "I am clicked!", Toast.LENGTH_SHORT).show();
        }
    }
}