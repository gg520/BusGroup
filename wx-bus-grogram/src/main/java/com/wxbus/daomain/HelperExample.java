package com.wxbus.daomain;

import java.util.ArrayList;
import java.util.List;

public class HelperExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table helper
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table helper
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table helper
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table helper
     *
     * @mbggenerated
     */
    public HelperExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table helper
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table helper
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table helper
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table helper
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table helper
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table helper
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table helper
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
     * This method corresponds to the database table helper
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
     * This method corresponds to the database table helper
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table helper
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
     * This class corresponds to the database table helper
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

        public Criteria andHelpIdIsNull() {
            addCriterion("help_Id is null");
            return (Criteria) this;
        }

        public Criteria andHelpIdIsNotNull() {
            addCriterion("help_Id is not null");
            return (Criteria) this;
        }

        public Criteria andHelpIdEqualTo(Integer value) {
            addCriterion("help_Id =", value, "helpId");
            return (Criteria) this;
        }

        public Criteria andHelpIdNotEqualTo(Integer value) {
            addCriterion("help_Id <>", value, "helpId");
            return (Criteria) this;
        }

        public Criteria andHelpIdGreaterThan(Integer value) {
            addCriterion("help_Id >", value, "helpId");
            return (Criteria) this;
        }

        public Criteria andHelpIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("help_Id >=", value, "helpId");
            return (Criteria) this;
        }

        public Criteria andHelpIdLessThan(Integer value) {
            addCriterion("help_Id <", value, "helpId");
            return (Criteria) this;
        }

        public Criteria andHelpIdLessThanOrEqualTo(Integer value) {
            addCriterion("help_Id <=", value, "helpId");
            return (Criteria) this;
        }

        public Criteria andHelpIdIn(List<Integer> values) {
            addCriterion("help_Id in", values, "helpId");
            return (Criteria) this;
        }

        public Criteria andHelpIdNotIn(List<Integer> values) {
            addCriterion("help_Id not in", values, "helpId");
            return (Criteria) this;
        }

        public Criteria andHelpIdBetween(Integer value1, Integer value2) {
            addCriterion("help_Id between", value1, value2, "helpId");
            return (Criteria) this;
        }

        public Criteria andHelpIdNotBetween(Integer value1, Integer value2) {
            addCriterion("help_Id not between", value1, value2, "helpId");
            return (Criteria) this;
        }

        public Criteria andHelpNameIsNull() {
            addCriterion("help_name is null");
            return (Criteria) this;
        }

        public Criteria andHelpNameIsNotNull() {
            addCriterion("help_name is not null");
            return (Criteria) this;
        }

        public Criteria andHelpNameEqualTo(String value) {
            addCriterion("help_name =", value, "helpName");
            return (Criteria) this;
        }

        public Criteria andHelpNameNotEqualTo(String value) {
            addCriterion("help_name <>", value, "helpName");
            return (Criteria) this;
        }

        public Criteria andHelpNameGreaterThan(String value) {
            addCriterion("help_name >", value, "helpName");
            return (Criteria) this;
        }

        public Criteria andHelpNameGreaterThanOrEqualTo(String value) {
            addCriterion("help_name >=", value, "helpName");
            return (Criteria) this;
        }

        public Criteria andHelpNameLessThan(String value) {
            addCriterion("help_name <", value, "helpName");
            return (Criteria) this;
        }

        public Criteria andHelpNameLessThanOrEqualTo(String value) {
            addCriterion("help_name <=", value, "helpName");
            return (Criteria) this;
        }

        public Criteria andHelpNameLike(String value) {
            addCriterion("help_name like", value, "helpName");
            return (Criteria) this;
        }

        public Criteria andHelpNameNotLike(String value) {
            addCriterion("help_name not like", value, "helpName");
            return (Criteria) this;
        }

        public Criteria andHelpNameIn(List<String> values) {
            addCriterion("help_name in", values, "helpName");
            return (Criteria) this;
        }

        public Criteria andHelpNameNotIn(List<String> values) {
            addCriterion("help_name not in", values, "helpName");
            return (Criteria) this;
        }

        public Criteria andHelpNameBetween(String value1, String value2) {
            addCriterion("help_name between", value1, value2, "helpName");
            return (Criteria) this;
        }

        public Criteria andHelpNameNotBetween(String value1, String value2) {
            addCriterion("help_name not between", value1, value2, "helpName");
            return (Criteria) this;
        }

        public Criteria andHelpMessageIsNull() {
            addCriterion("help_message is null");
            return (Criteria) this;
        }

        public Criteria andHelpMessageIsNotNull() {
            addCriterion("help_message is not null");
            return (Criteria) this;
        }

        public Criteria andHelpMessageEqualTo(String value) {
            addCriterion("help_message =", value, "helpMessage");
            return (Criteria) this;
        }

        public Criteria andHelpMessageNotEqualTo(String value) {
            addCriterion("help_message <>", value, "helpMessage");
            return (Criteria) this;
        }

        public Criteria andHelpMessageGreaterThan(String value) {
            addCriterion("help_message >", value, "helpMessage");
            return (Criteria) this;
        }

        public Criteria andHelpMessageGreaterThanOrEqualTo(String value) {
            addCriterion("help_message >=", value, "helpMessage");
            return (Criteria) this;
        }

        public Criteria andHelpMessageLessThan(String value) {
            addCriterion("help_message <", value, "helpMessage");
            return (Criteria) this;
        }

        public Criteria andHelpMessageLessThanOrEqualTo(String value) {
            addCriterion("help_message <=", value, "helpMessage");
            return (Criteria) this;
        }

        public Criteria andHelpMessageLike(String value) {
            addCriterion("help_message like", value, "helpMessage");
            return (Criteria) this;
        }

        public Criteria andHelpMessageNotLike(String value) {
            addCriterion("help_message not like", value, "helpMessage");
            return (Criteria) this;
        }

        public Criteria andHelpMessageIn(List<String> values) {
            addCriterion("help_message in", values, "helpMessage");
            return (Criteria) this;
        }

        public Criteria andHelpMessageNotIn(List<String> values) {
            addCriterion("help_message not in", values, "helpMessage");
            return (Criteria) this;
        }

        public Criteria andHelpMessageBetween(String value1, String value2) {
            addCriterion("help_message between", value1, value2, "helpMessage");
            return (Criteria) this;
        }

        public Criteria andHelpMessageNotBetween(String value1, String value2) {
            addCriterion("help_message not between", value1, value2, "helpMessage");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table helper
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
     * This class corresponds to the database table helper
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