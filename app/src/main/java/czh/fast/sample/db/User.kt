package czh.fast.sample.db

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel

@Table(database = AppDatabase::class)
class User : BaseModel() {

    @PrimaryKey(autoincrement = true)
    var id: Int = 0

    @Column
    var urerName: String? = null

    @Column
    var urerAge: Int? = null

}