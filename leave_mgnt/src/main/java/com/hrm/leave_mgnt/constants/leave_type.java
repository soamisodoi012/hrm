package com.hrm.leave_mgnt.constants;
    public enum leave_type {
        ANNUAL_LEAVE("AL"),
        SICK_LEAVE("SL"),
        MATERNITY_LEAVE("ML"),
        PATERNITY_LEAVE("PL"),
        CASUAL_LEAVE("CL");
        private final String code;
    
        leave_type(String code) {
            this.code = code;
        }
        public String getCode() {
            return code;
        }
    }
    


