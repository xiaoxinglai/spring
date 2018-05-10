package sell.dao.DO;

import org.apache.ibatis.annotations.Param;
import sell.pojo.FightInfo;

import java.util.List;

public interface FightInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fight_info
     *
     * @mbggenerated
     */
    int selectCount();
    List<FightInfo>  selectListByPage(@Param("currentPage")  Integer currentPage, @Param("pageSize") Integer pageSize);

    List<FightInfo> queryFightInfo(@Param("Departure") String Departure,@Param("Destination") String Destination,@Param("CompanyId")  Long CompanyId);
    int deleteByPrimaryKey(Long fightId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fight_info
     *
     * @mbggenerated
     */
    int insert(FightInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fight_info
     *
     * @mbggenerated
     */
    FightInfo selectByPrimaryKey(Long fightId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fight_info
     *
     * @mbggenerated
     */
    List<FightInfo> selectAll();

    List<FightInfo> selectAllByFids(List<Long> fightIds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fight_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(FightInfo record);
}