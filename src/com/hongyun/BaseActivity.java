/**
* @Title: BaseActivity.java
* @Package com.hongyun
* @Description: TODO(用一句话描述该文件做什么)
* @author andymao@qq.com
* @date May 11, 2012 7:51:31 AM
* @version V1.0
*/
package com.hongyun;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * @ClassName: BaseActivity
 * @Description: TODO:
 * @author: andymao@qq.com
 * @date May 11, 2012 7:51:31 AM
 *
 */
public class BaseActivity extends Activity {
    /* (non-Javadoc)
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        // TODO Auto-generated method stub
        super.onMenuItemSelected(featureId, item);
        switch (item.getItemId()) {
            case R.id.item1:
                item1Clicked();
                break;
            case R.id.item2:
                item2Clicked();
            default:
                break;
        }
        return true;
    }
    /**
     * item1 点击发生的事情，这里是个templateMethod
     * andymao@qq.com, May 16, 2012 9:55:31 PM
     */
    protected void item1Clicked() {

    }
    protected void item2Clicked() {

    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        // TODO Auto-generated method stub
        return super.onMenuOpened(featureId, menu);
    }
}
