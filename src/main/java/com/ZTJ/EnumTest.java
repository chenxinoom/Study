package com.ZTJ;

public class EnumTest {
     static enum JavaPlatformState {
        //  定义state
        OPEN{
            @Override void exit(JavaPlatformMachine pm){super.exit(pm);}

            @Override void valid(JavaPlatformMachine pm){
                this.exit(pm);
                if(pm.data.getValid_()){
                    pm.state =STEP1;
                }else{
                    NotFound();
                    pm.state =OFF;
                }
                pm.state.entry(pm);
            }

            @Override
            void first(JavaPlatformMachine pm) {}

            @Override
            void businessLine(JavaPlatformMachine pm) {}

            @Override
            void district(JavaPlatformMachine pm) {}
        },
        STEP1{
            @Override void exit(JavaPlatformMachine pm){super.exit(pm);}

            @Override
            void valid(JavaPlatformMachine pm) {}

            @Override void first(JavaPlatformMachine pm){
                this.exit(pm);
                if(!pm.data.getFirst_()){
                    pm.state =STEP2;
                }else{
                    ReturnDimension();
                    pm.state =OFF;
                }
                pm.state.entry(pm);
            }
            @Override
            void businessLine(JavaPlatformMachine pm) {}

            @Override
            void district(JavaPlatformMachine pm) {}
            },
            STEP2{
                @Override void exit(JavaPlatformMachine pm){super.exit(pm);}

                @Override
                void valid(JavaPlatformMachine pm) {}

                @Override void first(JavaPlatformMachine pm){

                }
                @Override
                void businessLine(JavaPlatformMachine pm) {
                    this.exit(pm);
                    if(pm.data.getBusinessLine_()) {
                        pm.state = OFF;
                    }
                    pm.state.entry(pm);
                }

                @Override
                void district(JavaPlatformMachine pm) {}
            },OFF{
             @Override void exit(JavaPlatformMachine pm){super.exit(pm);}

             @Override
             void valid(JavaPlatformMachine pm) {}

             @Override void first(JavaPlatformMachine pm){

             }
             @Override
             void businessLine(JavaPlatformMachine pm) {}

             @Override
             void district(JavaPlatformMachine pm) {
                 this.exit(pm);

                 pm.state.entry(pm);
             }
        };



        //状态模式 提取的接口  在常量实体类中实现抽象方法
        void entry(JavaPlatformMachine pm){System.out.println("→"+pm.state.name());}
         void exit(JavaPlatformMachine pm){System.out.println(pm.state.name()+"→ ");}

         abstract void valid(JavaPlatformMachine pm);
         abstract void first(JavaPlatformMachine pm);
         abstract void businessLine(JavaPlatformMachine pm);
         abstract void district(JavaPlatformMachine pm);

         void NotFound(){System.out.println("NotFound");}
         void ReturnDimension(){System.out.println("ReturnDimension");}
         void PreciseAdvertising(){System.out.println("PreciseAdvertising");}
         void Top9(){System.out.println("Top9");}
    }

    //触发的事件
    static class ContextData {
        private Boolean isValid_;//广告位是否有效
        private Boolean isFirst_;//是否第一次请求
        private Boolean isBusinessLine_;//是否属于业务线广告位
        private Boolean district_;//是否有地域

        public Boolean getValid_() {
            return isValid_;
        }

        public void setValid_(Boolean valid_) {
            isValid_ = valid_;
        }

        public Boolean getFirst_() {
            return isFirst_;
        }

        public void setFirst_(Boolean first_) {
            isFirst_ = first_;
        }

        public Boolean getBusinessLine_() {
            return isBusinessLine_;
        }

        public void setBusinessLine_(Boolean businessLine_) {
            isBusinessLine_ = businessLine_;
        }

        public Boolean getDistrict_() {
            return district_;
        }

        public void setDistrict_(Boolean district_) {
            this.district_ = district_;
        }
    }

    //管理
    static class JavaPlatformMachine {
        ContextData data = new ContextData();
        JavaPlatformState state = JavaPlatformState.OPEN; //旧的 在状态中设置为新的
        //Action
        public void valid(){state.valid(this);}
        public void first(){state.first(this);}
        public void businessLine(){state.businessLine(this);}
        public void district(){state.district(this);}
    }

    public static void main(String[] args) {
        JavaPlatformMachine pm = new JavaPlatformMachine();
        pm.data.setValid_(true);// 广告位是否有效
        pm.data.setFirst_(false);// 是否第一次请求
        pm.data.setBusinessLine_(true);//是否属于业务线广告位
        pm.data.setDistrict_(true);//是否有地域
        pm.valid();
        pm.first();
        pm.businessLine();
        pm.district();
    }
}
