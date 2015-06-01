package ir.madjeed.healthcare.gui.patient;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import ir.madjeed.healthcare.R;
import ir.madjeed.healthcare.facade.PhysicalFacade;
import ir.madjeed.healthcare.gui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import ir.madjeed.healthcare.gui.base.CustomRowObject;
import lecho.lib.hellocharts.listener.ViewportChangeListener;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.PreviewColumnChartView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


public class PhysicalStateActivity extends BaseActivity {

    private PhysicalFacade facade;
    private String patient_id;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        facade = new PhysicalFacade(this);
        super.onCreate(savedInstanceState);

        patient_id = getIntent().getExtras().getString("ID");

        ArrayList<CustomRowObject> patientAllPhysicalStates = facade.getPatientAllPhysicalStates(patient_id);
        ArrayList<Integer> ghand = new ArrayList<Integer>();
        ArrayList<Integer> vazn = new ArrayList<Integer>();
        ArrayList<Integer> feshar = new ArrayList<Integer>();
        ArrayList<Integer> ghandeKhun = new ArrayList<Integer>();
        for (int i = 0; i < patientAllPhysicalStates.size(); i++) {
            ghand.add(Integer.valueOf(patientAllPhysicalStates.get(i).getColumn(0)));
            vazn.add(Integer.valueOf(patientAllPhysicalStates.get(i).getColumn(1)));
            feshar.add(Integer.valueOf(patientAllPhysicalStates.get(i).getColumn(2)));
            ghandeKhun.add(Integer.valueOf(patientAllPhysicalStates.get(i).getColumn(3)));
        }

        if (savedInstanceState == null) {
            PlaceholderFragment myFragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putIntegerArrayList("ghand", ghand);
            args.putIntegerArrayList("vazn", vazn);
            args.putIntegerArrayList("feshar", feshar);
            args.putIntegerArrayList("ghandeKhun", ghandeKhun);
            myFragment.setArguments(args);
            int commit = getSupportFragmentManager().beginTransaction().add(R.id.container, myFragment).commit();
        }
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_line_chart;
    }


    /**
     * A fragment containing a line chart.
     */
    public static class PlaceholderFragment extends Fragment {

        private ColumnChartView chart;
        private PreviewColumnChartView previewChart;
        private ColumnChartData data;
        /**
         * Deep copy of data.
         */
        private ColumnChartData previewData;


        private ArrayList<Integer> ghand;
        private ArrayList<Integer> vazn;
        private ArrayList<Integer> feshar;
        private ArrayList<Integer> ghandeKhun;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            Bundle bundle = this.getArguments();
            ghand = bundle.getIntegerArrayList("ghand");
            vazn = bundle.getIntegerArrayList("vazn");
            feshar = bundle.getIntegerArrayList("feshar");
            ghandeKhun = bundle.getIntegerArrayList("ghandeKhun");


            setHasOptionsMenu(true);
            View rootView = inflater.inflate(R.layout.fragment_preview_column_chart, container, false);

            chart = (ColumnChartView) rootView.findViewById(R.id.chart);
            previewChart = (PreviewColumnChartView) rootView.findViewById(R.id.chart_preview);

            // Generate data for previewed chart and copy of that data for preview chart.
            generateDefaultData();

            chart.setColumnChartData(data);
            // Disable zoom/scroll for previewed chart, visible chart ranges depends on preview chart viewport so
            // zoom/scroll is unnecessary.
            chart.setZoomEnabled(false);
            chart.setScrollEnabled(false);

            previewChart.setColumnChartData(previewData);
            previewChart.setViewportChangeListener(new ViewportListener());

            previewX(false);

            return rootView;
        }

        // MENU
        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            inflater.inflate(R.menu.preview_column_chart, menu);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == R.id.action_reset) {
                generateDefaultData();
                chart.setColumnChartData(data);
                previewChart.setColumnChartData(previewData);
                previewX(true);
                return true;
            }
            if (id == R.id.action_preview_both) {
                previewXY();
                previewChart.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);
                return true;
            }
            if (id == R.id.action_preview_horizontal) {
                previewX(true);
                return true;
            }
            if (id == R.id.action_preview_vertical) {
                previewY();
                return true;
            }
            if (id == R.id.action_change_color) {
                int color = ChartUtils.pickColor();
                while (color == previewChart.getPreviewColor()) {
                    color = ChartUtils.pickColor();
                }
                previewChart.setPreviewColor(color);
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

        private void generateDefaultData() {
            int numSubcolumns = 4;
            List<Column> columns = new ArrayList<Column>();
            List<SubcolumnValue> values;

            int [] colors = new int [numSubcolumns];
            int i=0;
            while(i < numSubcolumns) {
                boolean flag = false;
                int temp = ChartUtils.pickColor();
                for (int j = 0; j < i; j++) {
                    if(colors[j]==temp)
                        flag = true;
                }
                if(!flag){
                    colors[i] = temp;
                    i++;
                }
            }

            for (int k = 0; k < ghand.size(); ++k) {

                values = new ArrayList<SubcolumnValue>();
                values.add(new SubcolumnValue(ghand.get(k), colors[0]));
                values.add(new SubcolumnValue(vazn.get(k), colors[1]));
                values.add(new SubcolumnValue(feshar.get(k), colors[2]));
                values.add(new SubcolumnValue(ghandeKhun.get(k), colors[3]));
                columns.add(new Column(values));
            }

            data = new ColumnChartData(columns);
            data.setAxisXBottom(new Axis());
            data.setAxisYLeft(new Axis().setHasLines(true));

            // prepare preview data, is better to use separate deep copy for preview chart.
            // set color to grey to make preview area more visible.
            previewData = new ColumnChartData(data);
            for (Column column : previewData.getColumns()) {
                for (SubcolumnValue value : column.getValues()) {
                    value.setColor(ChartUtils.DEFAULT_DARKEN_COLOR);
                }
            }

        }

        private void previewY() {
            Viewport tempViewport = new Viewport(chart.getMaximumViewport());
            float dy = tempViewport.height() / 4;
            tempViewport.inset(0, dy);
            previewChart.setCurrentViewportWithAnimation(tempViewport);
            previewChart.setZoomType(ZoomType.VERTICAL);
        }

        private void previewX(boolean animate) {
            Viewport tempViewport = new Viewport(chart.getMaximumViewport());
            float dx = tempViewport.width() / 4;
            tempViewport.inset(dx, 0);
            if(animate){
                previewChart.setCurrentViewportWithAnimation(tempViewport);
            }else {
                previewChart.setCurrentViewport(tempViewport);
            }
            previewChart.setZoomType(ZoomType.HORIZONTAL);
        }

        private void previewXY() {
            // Better to not modify viewport of any chart directly so create a copy.
            Viewport tempViewport = new Viewport(chart.getMaximumViewport());
            // Make temp viewport smaller.
            float dx = tempViewport.width() / 4;
            float dy = tempViewport.height() / 4;
            tempViewport.inset(dx, dy);
            previewChart.setCurrentViewportWithAnimation(tempViewport);
        }

        /**
         * Viewport listener for preview chart(lower one). in {@link #onViewportChanged(Viewport)} method change
         * viewport of upper chart.
         */
        private class ViewportListener implements ViewportChangeListener {

            @Override
            public void onViewportChanged(Viewport newViewport) {
                // don't use animation, it is unnecessary when using preview chart because usually viewport changes
                // happens to often.
                chart.setCurrentViewport(newViewport);
            }

        }
    }


}
