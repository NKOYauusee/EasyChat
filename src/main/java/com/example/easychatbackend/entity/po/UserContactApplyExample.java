package com.example.easychatbackend.entity.po;

import java.util.ArrayList;
import java.util.List;

public class UserContactApplyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserContactApplyExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        public Criteria andApplyIdIsNull() {
            addCriterion("apply_id is null");
            return (Criteria) this;
        }

        public Criteria andApplyIdIsNotNull() {
            addCriterion("apply_id is not null");
            return (Criteria) this;
        }

        public Criteria andApplyIdEqualTo(Integer value) {
            addCriterion("apply_id =", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdNotEqualTo(Integer value) {
            addCriterion("apply_id <>", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdGreaterThan(Integer value) {
            addCriterion("apply_id >", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("apply_id >=", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdLessThan(Integer value) {
            addCriterion("apply_id <", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdLessThanOrEqualTo(Integer value) {
            addCriterion("apply_id <=", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdIn(List<Integer> values) {
            addCriterion("apply_id in", values, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdNotIn(List<Integer> values) {
            addCriterion("apply_id not in", values, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdBetween(Integer value1, Integer value2) {
            addCriterion("apply_id between", value1, value2, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("apply_id not between", value1, value2, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyUserIdIsNull() {
            addCriterion("apply_user_id is null");
            return (Criteria) this;
        }

        public Criteria andApplyUserIdIsNotNull() {
            addCriterion("apply_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andApplyUserIdEqualTo(String value) {
            addCriterion("apply_user_id =", value, "applyUserId");
            return (Criteria) this;
        }

        public Criteria andApplyUserIdNotEqualTo(String value) {
            addCriterion("apply_user_id <>", value, "applyUserId");
            return (Criteria) this;
        }

        public Criteria andApplyUserIdGreaterThan(String value) {
            addCriterion("apply_user_id >", value, "applyUserId");
            return (Criteria) this;
        }

        public Criteria andApplyUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("apply_user_id >=", value, "applyUserId");
            return (Criteria) this;
        }

        public Criteria andApplyUserIdLessThan(String value) {
            addCriterion("apply_user_id <", value, "applyUserId");
            return (Criteria) this;
        }

        public Criteria andApplyUserIdLessThanOrEqualTo(String value) {
            addCriterion("apply_user_id <=", value, "applyUserId");
            return (Criteria) this;
        }

        public Criteria andApplyUserIdLike(String value) {
            addCriterion("apply_user_id like", value, "applyUserId");
            return (Criteria) this;
        }

        public Criteria andApplyUserIdNotLike(String value) {
            addCriterion("apply_user_id not like", value, "applyUserId");
            return (Criteria) this;
        }

        public Criteria andApplyUserIdIn(List<String> values) {
            addCriterion("apply_user_id in", values, "applyUserId");
            return (Criteria) this;
        }

        public Criteria andApplyUserIdNotIn(List<String> values) {
            addCriterion("apply_user_id not in", values, "applyUserId");
            return (Criteria) this;
        }

        public Criteria andApplyUserIdBetween(String value1, String value2) {
            addCriterion("apply_user_id between", value1, value2, "applyUserId");
            return (Criteria) this;
        }

        public Criteria andApplyUserIdNotBetween(String value1, String value2) {
            addCriterion("apply_user_id not between", value1, value2, "applyUserId");
            return (Criteria) this;
        }

        public Criteria andReceiverUserIdIsNull() {
            addCriterion("receiver_user_id is null");
            return (Criteria) this;
        }

        public Criteria andReceiverUserIdIsNotNull() {
            addCriterion("receiver_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverUserIdEqualTo(String value) {
            addCriterion("receiver_user_id =", value, "receiverUserId");
            return (Criteria) this;
        }

        public Criteria andReceiverUserIdNotEqualTo(String value) {
            addCriterion("receiver_user_id <>", value, "receiverUserId");
            return (Criteria) this;
        }

        public Criteria andReceiverUserIdGreaterThan(String value) {
            addCriterion("receiver_user_id >", value, "receiverUserId");
            return (Criteria) this;
        }

        public Criteria andReceiverUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("receiver_user_id >=", value, "receiverUserId");
            return (Criteria) this;
        }

        public Criteria andReceiverUserIdLessThan(String value) {
            addCriterion("receiver_user_id <", value, "receiverUserId");
            return (Criteria) this;
        }

        public Criteria andReceiverUserIdLessThanOrEqualTo(String value) {
            addCriterion("receiver_user_id <=", value, "receiverUserId");
            return (Criteria) this;
        }

        public Criteria andReceiverUserIdLike(String value) {
            addCriterion("receiver_user_id like", value, "receiverUserId");
            return (Criteria) this;
        }

        public Criteria andReceiverUserIdNotLike(String value) {
            addCriterion("receiver_user_id not like", value, "receiverUserId");
            return (Criteria) this;
        }

        public Criteria andReceiverUserIdIn(List<String> values) {
            addCriterion("receiver_user_id in", values, "receiverUserId");
            return (Criteria) this;
        }

        public Criteria andReceiverUserIdNotIn(List<String> values) {
            addCriterion("receiver_user_id not in", values, "receiverUserId");
            return (Criteria) this;
        }

        public Criteria andReceiverUserIdBetween(String value1, String value2) {
            addCriterion("receiver_user_id between", value1, value2, "receiverUserId");
            return (Criteria) this;
        }

        public Criteria andReceiverUserIdNotBetween(String value1, String value2) {
            addCriterion("receiver_user_id not between", value1, value2, "receiverUserId");
            return (Criteria) this;
        }

        public Criteria andContactTypeIsNull() {
            addCriterion("contact_type is null");
            return (Criteria) this;
        }

        public Criteria andContactTypeIsNotNull() {
            addCriterion("contact_type is not null");
            return (Criteria) this;
        }

        public Criteria andContactTypeEqualTo(Boolean value) {
            addCriterion("contact_type =", value, "contactType");
            return (Criteria) this;
        }

        public Criteria andContactTypeNotEqualTo(Boolean value) {
            addCriterion("contact_type <>", value, "contactType");
            return (Criteria) this;
        }

        public Criteria andContactTypeGreaterThan(Boolean value) {
            addCriterion("contact_type >", value, "contactType");
            return (Criteria) this;
        }

        public Criteria andContactTypeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("contact_type >=", value, "contactType");
            return (Criteria) this;
        }

        public Criteria andContactTypeLessThan(Boolean value) {
            addCriterion("contact_type <", value, "contactType");
            return (Criteria) this;
        }

        public Criteria andContactTypeLessThanOrEqualTo(Boolean value) {
            addCriterion("contact_type <=", value, "contactType");
            return (Criteria) this;
        }

        public Criteria andContactTypeIn(List<Boolean> values) {
            addCriterion("contact_type in", values, "contactType");
            return (Criteria) this;
        }

        public Criteria andContactTypeNotIn(List<Boolean> values) {
            addCriterion("contact_type not in", values, "contactType");
            return (Criteria) this;
        }

        public Criteria andContactTypeBetween(Boolean value1, Boolean value2) {
            addCriterion("contact_type between", value1, value2, "contactType");
            return (Criteria) this;
        }

        public Criteria andContactTypeNotBetween(Boolean value1, Boolean value2) {
            addCriterion("contact_type not between", value1, value2, "contactType");
            return (Criteria) this;
        }

        public Criteria andContactIdIsNull() {
            addCriterion("contact_id is null");
            return (Criteria) this;
        }

        public Criteria andContactIdIsNotNull() {
            addCriterion("contact_id is not null");
            return (Criteria) this;
        }

        public Criteria andContactIdEqualTo(String value) {
            addCriterion("contact_id =", value, "contactId");
            return (Criteria) this;
        }

        public Criteria andContactIdNotEqualTo(String value) {
            addCriterion("contact_id <>", value, "contactId");
            return (Criteria) this;
        }

        public Criteria andContactIdGreaterThan(String value) {
            addCriterion("contact_id >", value, "contactId");
            return (Criteria) this;
        }

        public Criteria andContactIdGreaterThanOrEqualTo(String value) {
            addCriterion("contact_id >=", value, "contactId");
            return (Criteria) this;
        }

        public Criteria andContactIdLessThan(String value) {
            addCriterion("contact_id <", value, "contactId");
            return (Criteria) this;
        }

        public Criteria andContactIdLessThanOrEqualTo(String value) {
            addCriterion("contact_id <=", value, "contactId");
            return (Criteria) this;
        }

        public Criteria andContactIdLike(String value) {
            addCriterion("contact_id like", value, "contactId");
            return (Criteria) this;
        }

        public Criteria andContactIdNotLike(String value) {
            addCriterion("contact_id not like", value, "contactId");
            return (Criteria) this;
        }

        public Criteria andContactIdIn(List<String> values) {
            addCriterion("contact_id in", values, "contactId");
            return (Criteria) this;
        }

        public Criteria andContactIdNotIn(List<String> values) {
            addCriterion("contact_id not in", values, "contactId");
            return (Criteria) this;
        }

        public Criteria andContactIdBetween(String value1, String value2) {
            addCriterion("contact_id between", value1, value2, "contactId");
            return (Criteria) this;
        }

        public Criteria andContactIdNotBetween(String value1, String value2) {
            addCriterion("contact_id not between", value1, value2, "contactId");
            return (Criteria) this;
        }

        public Criteria andLastApplyTimeIsNull() {
            addCriterion("last_apply_time is null");
            return (Criteria) this;
        }

        public Criteria andLastApplyTimeIsNotNull() {
            addCriterion("last_apply_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastApplyTimeEqualTo(Long value) {
            addCriterion("last_apply_time =", value, "lastApplyTime");
            return (Criteria) this;
        }

        public Criteria andLastApplyTimeNotEqualTo(Long value) {
            addCriterion("last_apply_time <>", value, "lastApplyTime");
            return (Criteria) this;
        }

        public Criteria andLastApplyTimeGreaterThan(Long value) {
            addCriterion("last_apply_time >", value, "lastApplyTime");
            return (Criteria) this;
        }

        public Criteria andLastApplyTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("last_apply_time >=", value, "lastApplyTime");
            return (Criteria) this;
        }

        public Criteria andLastApplyTimeLessThan(Long value) {
            addCriterion("last_apply_time <", value, "lastApplyTime");
            return (Criteria) this;
        }

        public Criteria andLastApplyTimeLessThanOrEqualTo(Long value) {
            addCriterion("last_apply_time <=", value, "lastApplyTime");
            return (Criteria) this;
        }

        public Criteria andLastApplyTimeIn(List<Long> values) {
            addCriterion("last_apply_time in", values, "lastApplyTime");
            return (Criteria) this;
        }

        public Criteria andLastApplyTimeNotIn(List<Long> values) {
            addCriterion("last_apply_time not in", values, "lastApplyTime");
            return (Criteria) this;
        }

        public Criteria andLastApplyTimeBetween(Long value1, Long value2) {
            addCriterion("last_apply_time between", value1, value2, "lastApplyTime");
            return (Criteria) this;
        }

        public Criteria andLastApplyTimeNotBetween(Long value1, Long value2) {
            addCriterion("last_apply_time not between", value1, value2, "lastApplyTime");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Boolean value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Boolean value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Boolean value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Boolean value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Boolean value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Boolean> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Boolean> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Boolean value1, Boolean value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andApplyInfoIsNull() {
            addCriterion("apply_info is null");
            return (Criteria) this;
        }

        public Criteria andApplyInfoIsNotNull() {
            addCriterion("apply_info is not null");
            return (Criteria) this;
        }

        public Criteria andApplyInfoEqualTo(String value) {
            addCriterion("apply_info =", value, "applyInfo");
            return (Criteria) this;
        }

        public Criteria andApplyInfoNotEqualTo(String value) {
            addCriterion("apply_info <>", value, "applyInfo");
            return (Criteria) this;
        }

        public Criteria andApplyInfoGreaterThan(String value) {
            addCriterion("apply_info >", value, "applyInfo");
            return (Criteria) this;
        }

        public Criteria andApplyInfoGreaterThanOrEqualTo(String value) {
            addCriterion("apply_info >=", value, "applyInfo");
            return (Criteria) this;
        }

        public Criteria andApplyInfoLessThan(String value) {
            addCriterion("apply_info <", value, "applyInfo");
            return (Criteria) this;
        }

        public Criteria andApplyInfoLessThanOrEqualTo(String value) {
            addCriterion("apply_info <=", value, "applyInfo");
            return (Criteria) this;
        }

        public Criteria andApplyInfoLike(String value) {
            addCriterion("apply_info like", value, "applyInfo");
            return (Criteria) this;
        }

        public Criteria andApplyInfoNotLike(String value) {
            addCriterion("apply_info not like", value, "applyInfo");
            return (Criteria) this;
        }

        public Criteria andApplyInfoIn(List<String> values) {
            addCriterion("apply_info in", values, "applyInfo");
            return (Criteria) this;
        }

        public Criteria andApplyInfoNotIn(List<String> values) {
            addCriterion("apply_info not in", values, "applyInfo");
            return (Criteria) this;
        }

        public Criteria andApplyInfoBetween(String value1, String value2) {
            addCriterion("apply_info between", value1, value2, "applyInfo");
            return (Criteria) this;
        }

        public Criteria andApplyInfoNotBetween(String value1, String value2) {
            addCriterion("apply_info not between", value1, value2, "applyInfo");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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