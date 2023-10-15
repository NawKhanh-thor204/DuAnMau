package com.example.pnlib.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pnlib.Dao.ThongKeDAO;
import com.example.pnlib.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DoanhThu extends Fragment {

    ThongKeDAO thongKeDAO;
    int mDay, mYear, mMonth;
    EditText edtTuNgay, edtDenNgay;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public DoanhThu() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_doanh_thu, container, false);
        //
        thongKeDAO = new ThongKeDAO(getActivity());
        TextView txtDoanhThu = view.findViewById(R.id.txtDoanhThu);
        Button btnTinh = view.findViewById(R.id.btnXemDoanhThu);
         edtTuNgay = view.findViewById(R.id.edtTuNgay);
         edtDenNgay = view.findViewById(R.id.edtDenNgay);
        Calendar calendar = Calendar.getInstance();
        //
        edtTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mYear = calendar.get(Calendar.YEAR);
                mMonth = calendar.get(Calendar.MONTH);
                mDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(), 0, mDateTuNgay, mYear, mMonth, mDay);
                d.show();
            }
        });
        edtDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mYear = calendar.get(Calendar.YEAR);
                mMonth = calendar.get(Calendar.MONTH);
                mDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(), 0, mDateDenNgay, mYear, mMonth, mDay);
                d.show();
            }
        });

        btnTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tuNgay = edtTuNgay.getText().toString().trim();
                String denNgay = edtDenNgay.getText().toString().trim();

                if (tuNgay.equals("") || denNgay.equals("")) {
                    Toast.makeText(getActivity(), "Vui lòng chọn thời gian hợp lệ!", Toast.LENGTH_SHORT).show();
                    return;
                }
                txtDoanhThu.setText("Doanh thu: " + thongKeDAO.getDoanhThu(tuNgay, denNgay) + " VNĐ");
            }
        });
        return view;
    }

    DatePickerDialog.OnDateSetListener mDateDenNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
            edtDenNgay.setText(sdf.format(c.getTime()));
        }
    };
    DatePickerDialog.OnDateSetListener mDateTuNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
            edtTuNgay.setText(sdf.format(c.getTime()));
        }
    };

}