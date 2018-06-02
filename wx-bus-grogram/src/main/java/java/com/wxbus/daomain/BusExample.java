package java.com.wxbus.daomain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class BusExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table bus
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table bus
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table bus
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bus
     *
     * @mbggenerated
     */
    public BusExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bus
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bus
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bus
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bus
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bus
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bus
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bus
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bus
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bus
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bus
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table bus
     *
     * @mbggenerated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andBusNumIsNull() {
            addCriterion("bus_num is null");
            return (Criteria) this;
        }

        public Criteria andBusNumIsNotNull() {
            addCriterion("bus_num is not null");
            return (Criteria) this;
        }

        public Criteria andBusNumEqualTo(Integer value) {
            addCriterion("bus_num =", value, "busNum");
            return (Criteria) this;
        }

        public Criteria andBusNumNotEqualTo(Integer value) {
            addCriterion("bus_num <>", value, "busNum");
            return (Criteria) this;
        }

        public Criteria andBusNumGreaterThan(Integer value) {
            addCriterion("bus_num >", value, "busNum");
            return (Criteria) this;
        }

        public Criteria andBusNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("bus_num >=", value, "busNum");
            return (Criteria) this;
        }

        public Criteria andBusNumLessThan(Integer value) {
            addCriterion("bus_num <", value, "busNum");
            return (Criteria) this;
        }

        public Criteria andBusNumLessThanOrEqualTo(Integer value) {
            addCriterion("bus_num <=", value, "busNum");
            return (Criteria) this;
        }

        public Criteria andBusNumIn(List<Integer> values) {
            addCriterion("bus_num in", values, "busNum");
            return (Criteria) this;
        }

        public Criteria andBusNumNotIn(List<Integer> values) {
            addCriterion("bus_num not in", values, "busNum");
            return (Criteria) this;
        }

        public Criteria andBusNumBetween(Integer value1, Integer value2) {
            addCriterion("bus_num between", value1, value2, "busNum");
            return (Criteria) this;
        }

        public Criteria andBusNumNotBetween(Integer value1, Integer value2) {
            addCriterion("bus_num not between", value1, value2, "busNum");
            return (Criteria) this;
        }

        public Criteria andBusIdIsNull() {
            addCriterion("bus_id is null");
            return (Criteria) this;
        }

        public Criteria andBusIdIsNotNull() {
            addCriterion("bus_id is not null");
            return (Criteria) this;
        }

        public Criteria andBusIdEqualTo(String value) {
            addCriterion("bus_id =", value, "busId");
            return (Criteria) this;
        }

        public Criteria andBusIdNotEqualTo(String value) {
            addCriterion("bus_id <>", value, "busId");
            return (Criteria) this;
        }

        public Criteria andBusIdGreaterThan(String value) {
            addCriterion("bus_id >", value, "busId");
            return (Criteria) this;
        }

        public Criteria andBusIdGreaterThanOrEqualTo(String value) {
            addCriterion("bus_id >=", value, "busId");
            return (Criteria) this;
        }

        public Criteria andBusIdLessThan(String value) {
            addCriterion("bus_id <", value, "busId");
            return (Criteria) this;
        }

        public Criteria andBusIdLessThanOrEqualTo(String value) {
            addCriterion("bus_id <=", value, "busId");
            return (Criteria) this;
        }

        public Criteria andBusIdLike(String value) {
            addCriterion("bus_id like", value, "busId");
            return (Criteria) this;
        }

        public Criteria andBusIdNotLike(String value) {
            addCriterion("bus_id not like", value, "busId");
            return (Criteria) this;
        }

        public Criteria andBusIdIn(List<String> values) {
            addCriterion("bus_id in", values, "busId");
            return (Criteria) this;
        }

        public Criteria andBusIdNotIn(List<String> values) {
            addCriterion("bus_id not in", values, "busId");
            return (Criteria) this;
        }

        public Criteria andBusIdBetween(String value1, String value2) {
            addCriterion("bus_id between", value1, value2, "busId");
            return (Criteria) this;
        }

        public Criteria andBusIdNotBetween(String value1, String value2) {
            addCriterion("bus_id not between", value1, value2, "busId");
            return (Criteria) this;
        }

        public Criteria andBusTypeIsNull() {
            addCriterion("bus_type is null");
            return (Criteria) this;
        }

        public Criteria andBusTypeIsNotNull() {
            addCriterion("bus_type is not null");
            return (Criteria) this;
        }

        public Criteria andBusTypeEqualTo(String value) {
            addCriterion("bus_type =", value, "busType");
            return (Criteria) this;
        }

        public Criteria andBusTypeNotEqualTo(String value) {
            addCriterion("bus_type <>", value, "busType");
            return (Criteria) this;
        }

        public Criteria andBusTypeGreaterThan(String value) {
            addCriterion("bus_type >", value, "busType");
            return (Criteria) this;
        }

        public Criteria andBusTypeGreaterThanOrEqualTo(String value) {
            addCriterion("bus_type >=", value, "busType");
            return (Criteria) this;
        }

        public Criteria andBusTypeLessThan(String value) {
            addCriterion("bus_type <", value, "busType");
            return (Criteria) this;
        }

        public Criteria andBusTypeLessThanOrEqualTo(String value) {
            addCriterion("bus_type <=", value, "busType");
            return (Criteria) this;
        }

        public Criteria andBusTypeLike(String value) {
            addCriterion("bus_type like", value, "busType");
            return (Criteria) this;
        }

        public Criteria andBusTypeNotLike(String value) {
            addCriterion("bus_type not like", value, "busType");
            return (Criteria) this;
        }

        public Criteria andBusTypeIn(List<String> values) {
            addCriterion("bus_type in", values, "busType");
            return (Criteria) this;
        }

        public Criteria andBusTypeNotIn(List<String> values) {
            addCriterion("bus_type not in", values, "busType");
            return (Criteria) this;
        }

        public Criteria andBusTypeBetween(String value1, String value2) {
            addCriterion("bus_type between", value1, value2, "busType");
            return (Criteria) this;
        }

        public Criteria andBusTypeNotBetween(String value1, String value2) {
            addCriterion("bus_type not between", value1, value2, "busType");
            return (Criteria) this;
        }

        public Criteria andBusOwnerIsNull() {
            addCriterion("bus_owner is null");
            return (Criteria) this;
        }

        public Criteria andBusOwnerIsNotNull() {
            addCriterion("bus_owner is not null");
            return (Criteria) this;
        }

        public Criteria andBusOwnerEqualTo(String value) {
            addCriterion("bus_owner =", value, "busOwner");
            return (Criteria) this;
        }

        public Criteria andBusOwnerNotEqualTo(String value) {
            addCriterion("bus_owner <>", value, "busOwner");
            return (Criteria) this;
        }

        public Criteria andBusOwnerGreaterThan(String value) {
            addCriterion("bus_owner >", value, "busOwner");
            return (Criteria) this;
        }

        public Criteria andBusOwnerGreaterThanOrEqualTo(String value) {
            addCriterion("bus_owner >=", value, "busOwner");
            return (Criteria) this;
        }

        public Criteria andBusOwnerLessThan(String value) {
            addCriterion("bus_owner <", value, "busOwner");
            return (Criteria) this;
        }

        public Criteria andBusOwnerLessThanOrEqualTo(String value) {
            addCriterion("bus_owner <=", value, "busOwner");
            return (Criteria) this;
        }

        public Criteria andBusOwnerLike(String value) {
            addCriterion("bus_owner like", value, "busOwner");
            return (Criteria) this;
        }

        public Criteria andBusOwnerNotLike(String value) {
            addCriterion("bus_owner not like", value, "busOwner");
            return (Criteria) this;
        }

        public Criteria andBusOwnerIn(List<String> values) {
            addCriterion("bus_owner in", values, "busOwner");
            return (Criteria) this;
        }

        public Criteria andBusOwnerNotIn(List<String> values) {
            addCriterion("bus_owner not in", values, "busOwner");
            return (Criteria) this;
        }

        public Criteria andBusOwnerBetween(String value1, String value2) {
            addCriterion("bus_owner between", value1, value2, "busOwner");
            return (Criteria) this;
        }

        public Criteria andBusOwnerNotBetween(String value1, String value2) {
            addCriterion("bus_owner not between", value1, value2, "busOwner");
            return (Criteria) this;
        }

        public Criteria andCharacterIsNull() {
            addCriterion("character is null");
            return (Criteria) this;
        }

        public Criteria andCharacterIsNotNull() {
            addCriterion("character is not null");
            return (Criteria) this;
        }

        public Criteria andCharacterEqualTo(String value) {
            addCriterion("character =", value, "character");
            return (Criteria) this;
        }

        public Criteria andCharacterNotEqualTo(String value) {
            addCriterion("character <>", value, "character");
            return (Criteria) this;
        }

        public Criteria andCharacterGreaterThan(String value) {
            addCriterion("character >", value, "character");
            return (Criteria) this;
        }

        public Criteria andCharacterGreaterThanOrEqualTo(String value) {
            addCriterion("character >=", value, "character");
            return (Criteria) this;
        }

        public Criteria andCharacterLessThan(String value) {
            addCriterion("character <", value, "character");
            return (Criteria) this;
        }

        public Criteria andCharacterLessThanOrEqualTo(String value) {
            addCriterion("character <=", value, "character");
            return (Criteria) this;
        }

        public Criteria andCharacterLike(String value) {
            addCriterion("character like", value, "character");
            return (Criteria) this;
        }

        public Criteria andCharacterNotLike(String value) {
            addCriterion("character not like", value, "character");
            return (Criteria) this;
        }

        public Criteria andCharacterIn(List<String> values) {
            addCriterion("character in", values, "character");
            return (Criteria) this;
        }

        public Criteria andCharacterNotIn(List<String> values) {
            addCriterion("character not in", values, "character");
            return (Criteria) this;
        }

        public Criteria andCharacterBetween(String value1, String value2) {
            addCriterion("character between", value1, value2, "character");
            return (Criteria) this;
        }

        public Criteria andCharacterNotBetween(String value1, String value2) {
            addCriterion("character not between", value1, value2, "character");
            return (Criteria) this;
        }

        public Criteria andModelIsNull() {
            addCriterion("model is null");
            return (Criteria) this;
        }

        public Criteria andModelIsNotNull() {
            addCriterion("model is not null");
            return (Criteria) this;
        }

        public Criteria andModelEqualTo(String value) {
            addCriterion("model =", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelNotEqualTo(String value) {
            addCriterion("model <>", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelGreaterThan(String value) {
            addCriterion("model >", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelGreaterThanOrEqualTo(String value) {
            addCriterion("model >=", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelLessThan(String value) {
            addCriterion("model <", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelLessThanOrEqualTo(String value) {
            addCriterion("model <=", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelLike(String value) {
            addCriterion("model like", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelNotLike(String value) {
            addCriterion("model not like", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelIn(List<String> values) {
            addCriterion("model in", values, "model");
            return (Criteria) this;
        }

        public Criteria andModelNotIn(List<String> values) {
            addCriterion("model not in", values, "model");
            return (Criteria) this;
        }

        public Criteria andModelBetween(String value1, String value2) {
            addCriterion("model between", value1, value2, "model");
            return (Criteria) this;
        }

        public Criteria andModelNotBetween(String value1, String value2) {
            addCriterion("model not between", value1, value2, "model");
            return (Criteria) this;
        }

        public Criteria andVinIsNull() {
            addCriterion("VIN is null");
            return (Criteria) this;
        }

        public Criteria andVinIsNotNull() {
            addCriterion("VIN is not null");
            return (Criteria) this;
        }

        public Criteria andVinEqualTo(String value) {
            addCriterion("VIN =", value, "vin");
            return (Criteria) this;
        }

        public Criteria andVinNotEqualTo(String value) {
            addCriterion("VIN <>", value, "vin");
            return (Criteria) this;
        }

        public Criteria andVinGreaterThan(String value) {
            addCriterion("VIN >", value, "vin");
            return (Criteria) this;
        }

        public Criteria andVinGreaterThanOrEqualTo(String value) {
            addCriterion("VIN >=", value, "vin");
            return (Criteria) this;
        }

        public Criteria andVinLessThan(String value) {
            addCriterion("VIN <", value, "vin");
            return (Criteria) this;
        }

        public Criteria andVinLessThanOrEqualTo(String value) {
            addCriterion("VIN <=", value, "vin");
            return (Criteria) this;
        }

        public Criteria andVinLike(String value) {
            addCriterion("VIN like", value, "vin");
            return (Criteria) this;
        }

        public Criteria andVinNotLike(String value) {
            addCriterion("VIN not like", value, "vin");
            return (Criteria) this;
        }

        public Criteria andVinIn(List<String> values) {
            addCriterion("VIN in", values, "vin");
            return (Criteria) this;
        }

        public Criteria andVinNotIn(List<String> values) {
            addCriterion("VIN not in", values, "vin");
            return (Criteria) this;
        }

        public Criteria andVinBetween(String value1, String value2) {
            addCriterion("VIN between", value1, value2, "vin");
            return (Criteria) this;
        }

        public Criteria andVinNotBetween(String value1, String value2) {
            addCriterion("VIN not between", value1, value2, "vin");
            return (Criteria) this;
        }

        public Criteria andEngineNumIsNull() {
            addCriterion("engine_num is null");
            return (Criteria) this;
        }

        public Criteria andEngineNumIsNotNull() {
            addCriterion("engine_num is not null");
            return (Criteria) this;
        }

        public Criteria andEngineNumEqualTo(String value) {
            addCriterion("engine_num =", value, "engineNum");
            return (Criteria) this;
        }

        public Criteria andEngineNumNotEqualTo(String value) {
            addCriterion("engine_num <>", value, "engineNum");
            return (Criteria) this;
        }

        public Criteria andEngineNumGreaterThan(String value) {
            addCriterion("engine_num >", value, "engineNum");
            return (Criteria) this;
        }

        public Criteria andEngineNumGreaterThanOrEqualTo(String value) {
            addCriterion("engine_num >=", value, "engineNum");
            return (Criteria) this;
        }

        public Criteria andEngineNumLessThan(String value) {
            addCriterion("engine_num <", value, "engineNum");
            return (Criteria) this;
        }

        public Criteria andEngineNumLessThanOrEqualTo(String value) {
            addCriterion("engine_num <=", value, "engineNum");
            return (Criteria) this;
        }

        public Criteria andEngineNumLike(String value) {
            addCriterion("engine_num like", value, "engineNum");
            return (Criteria) this;
        }

        public Criteria andEngineNumNotLike(String value) {
            addCriterion("engine_num not like", value, "engineNum");
            return (Criteria) this;
        }

        public Criteria andEngineNumIn(List<String> values) {
            addCriterion("engine_num in", values, "engineNum");
            return (Criteria) this;
        }

        public Criteria andEngineNumNotIn(List<String> values) {
            addCriterion("engine_num not in", values, "engineNum");
            return (Criteria) this;
        }

        public Criteria andEngineNumBetween(String value1, String value2) {
            addCriterion("engine_num between", value1, value2, "engineNum");
            return (Criteria) this;
        }

        public Criteria andEngineNumNotBetween(String value1, String value2) {
            addCriterion("engine_num not between", value1, value2, "engineNum");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateIsNull() {
            addCriterion("registration_date is null");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateIsNotNull() {
            addCriterion("registration_date is not null");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateEqualTo(Date value) {
            addCriterionForJDBCDate("registration_date =", value, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("registration_date <>", value, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateGreaterThan(Date value) {
            addCriterionForJDBCDate("registration_date >", value, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("registration_date >=", value, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateLessThan(Date value) {
            addCriterionForJDBCDate("registration_date <", value, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("registration_date <=", value, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateIn(List<Date> values) {
            addCriterionForJDBCDate("registration_date in", values, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("registration_date not in", values, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("registration_date between", value1, value2, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("registration_date not between", value1, value2, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andOppenDateIsNull() {
            addCriterion("oppen_date is null");
            return (Criteria) this;
        }

        public Criteria andOppenDateIsNotNull() {
            addCriterion("oppen_date is not null");
            return (Criteria) this;
        }

        public Criteria andOppenDateEqualTo(Date value) {
            addCriterionForJDBCDate("oppen_date =", value, "oppenDate");
            return (Criteria) this;
        }

        public Criteria andOppenDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("oppen_date <>", value, "oppenDate");
            return (Criteria) this;
        }

        public Criteria andOppenDateGreaterThan(Date value) {
            addCriterionForJDBCDate("oppen_date >", value, "oppenDate");
            return (Criteria) this;
        }

        public Criteria andOppenDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("oppen_date >=", value, "oppenDate");
            return (Criteria) this;
        }

        public Criteria andOppenDateLessThan(Date value) {
            addCriterionForJDBCDate("oppen_date <", value, "oppenDate");
            return (Criteria) this;
        }

        public Criteria andOppenDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("oppen_date <=", value, "oppenDate");
            return (Criteria) this;
        }

        public Criteria andOppenDateIn(List<Date> values) {
            addCriterionForJDBCDate("oppen_date in", values, "oppenDate");
            return (Criteria) this;
        }

        public Criteria andOppenDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("oppen_date not in", values, "oppenDate");
            return (Criteria) this;
        }

        public Criteria andOppenDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("oppen_date between", value1, value2, "oppenDate");
            return (Criteria) this;
        }

        public Criteria andOppenDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("oppen_date not between", value1, value2, "oppenDate");
            return (Criteria) this;
        }

        public Criteria andSeatingIsNull() {
            addCriterion("seating is null");
            return (Criteria) this;
        }

        public Criteria andSeatingIsNotNull() {
            addCriterion("seating is not null");
            return (Criteria) this;
        }

        public Criteria andSeatingEqualTo(Integer value) {
            addCriterion("seating =", value, "seating");
            return (Criteria) this;
        }

        public Criteria andSeatingNotEqualTo(Integer value) {
            addCriterion("seating <>", value, "seating");
            return (Criteria) this;
        }

        public Criteria andSeatingGreaterThan(Integer value) {
            addCriterion("seating >", value, "seating");
            return (Criteria) this;
        }

        public Criteria andSeatingGreaterThanOrEqualTo(Integer value) {
            addCriterion("seating >=", value, "seating");
            return (Criteria) this;
        }

        public Criteria andSeatingLessThan(Integer value) {
            addCriterion("seating <", value, "seating");
            return (Criteria) this;
        }

        public Criteria andSeatingLessThanOrEqualTo(Integer value) {
            addCriterion("seating <=", value, "seating");
            return (Criteria) this;
        }

        public Criteria andSeatingIn(List<Integer> values) {
            addCriterion("seating in", values, "seating");
            return (Criteria) this;
        }

        public Criteria andSeatingNotIn(List<Integer> values) {
            addCriterion("seating not in", values, "seating");
            return (Criteria) this;
        }

        public Criteria andSeatingBetween(Integer value1, Integer value2) {
            addCriterion("seating between", value1, value2, "seating");
            return (Criteria) this;
        }

        public Criteria andSeatingNotBetween(Integer value1, Integer value2) {
            addCriterion("seating not between", value1, value2, "seating");
            return (Criteria) this;
        }

        public Criteria andBusStatusIsNull() {
            addCriterion("bus_status is null");
            return (Criteria) this;
        }

        public Criteria andBusStatusIsNotNull() {
            addCriterion("bus_status is not null");
            return (Criteria) this;
        }

        public Criteria andBusStatusEqualTo(Integer value) {
            addCriterion("bus_status =", value, "busStatus");
            return (Criteria) this;
        }

        public Criteria andBusStatusNotEqualTo(Integer value) {
            addCriterion("bus_status <>", value, "busStatus");
            return (Criteria) this;
        }

        public Criteria andBusStatusGreaterThan(Integer value) {
            addCriterion("bus_status >", value, "busStatus");
            return (Criteria) this;
        }

        public Criteria andBusStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("bus_status >=", value, "busStatus");
            return (Criteria) this;
        }

        public Criteria andBusStatusLessThan(Integer value) {
            addCriterion("bus_status <", value, "busStatus");
            return (Criteria) this;
        }

        public Criteria andBusStatusLessThanOrEqualTo(Integer value) {
            addCriterion("bus_status <=", value, "busStatus");
            return (Criteria) this;
        }

        public Criteria andBusStatusIn(List<Integer> values) {
            addCriterion("bus_status in", values, "busStatus");
            return (Criteria) this;
        }

        public Criteria andBusStatusNotIn(List<Integer> values) {
            addCriterion("bus_status not in", values, "busStatus");
            return (Criteria) this;
        }

        public Criteria andBusStatusBetween(Integer value1, Integer value2) {
            addCriterion("bus_status between", value1, value2, "busStatus");
            return (Criteria) this;
        }

        public Criteria andBusStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("bus_status not between", value1, value2, "busStatus");
            return (Criteria) this;
        }

        public Criteria andBusMarkIsNull() {
            addCriterion("bus_mark is null");
            return (Criteria) this;
        }

        public Criteria andBusMarkIsNotNull() {
            addCriterion("bus_mark is not null");
            return (Criteria) this;
        }

        public Criteria andBusMarkEqualTo(Integer value) {
            addCriterion("bus_mark =", value, "busMark");
            return (Criteria) this;
        }

        public Criteria andBusMarkNotEqualTo(Integer value) {
            addCriterion("bus_mark <>", value, "busMark");
            return (Criteria) this;
        }

        public Criteria andBusMarkGreaterThan(Integer value) {
            addCriterion("bus_mark >", value, "busMark");
            return (Criteria) this;
        }

        public Criteria andBusMarkGreaterThanOrEqualTo(Integer value) {
            addCriterion("bus_mark >=", value, "busMark");
            return (Criteria) this;
        }

        public Criteria andBusMarkLessThan(Integer value) {
            addCriterion("bus_mark <", value, "busMark");
            return (Criteria) this;
        }

        public Criteria andBusMarkLessThanOrEqualTo(Integer value) {
            addCriterion("bus_mark <=", value, "busMark");
            return (Criteria) this;
        }

        public Criteria andBusMarkIn(List<Integer> values) {
            addCriterion("bus_mark in", values, "busMark");
            return (Criteria) this;
        }

        public Criteria andBusMarkNotIn(List<Integer> values) {
            addCriterion("bus_mark not in", values, "busMark");
            return (Criteria) this;
        }

        public Criteria andBusMarkBetween(Integer value1, Integer value2) {
            addCriterion("bus_mark between", value1, value2, "busMark");
            return (Criteria) this;
        }

        public Criteria andBusMarkNotBetween(Integer value1, Integer value2) {
            addCriterion("bus_mark not between", value1, value2, "busMark");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table bus
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table bus
     *
     * @mbggenerated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}