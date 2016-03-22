public class EntityToStringUtil {

	public static <E extends CommonEntity> List<String> getStringList(CommonEntity entity) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		List<String> strList = new ArrayList<String>();

		Class<? extends CommonEntity> clazz = entity.getClass();
		Field[] fields = clazz.getDeclaredFields();

		for(Field field : fields){
			String valueStr = "";

			if(field.getName().startsWith("serialVersionUID")){
				continue;
			}

			PropertyDescriptor nameProp = new PropertyDescriptor(field.getName(), clazz);
			Method nameGetter = nameProp.getReadMethod();
			Object valueObj = (Object) nameGetter.invoke(entity, (Object[]) null);
			if(valueObj == null){
				strList.add(valueStr);
				continue;
			}

			if(valueObj instanceof String){
				valueStr = (String)valueObj;
			}else if(valueObj instanceof BigDecimal){
				valueStr = ((BigDecimal)valueObj).toString();
			}

			strList.add(valueStr);

		}

		strList.add("TEST");
		strList.add("2017-02-10 01:01:01.000");

		return strList;
	}
}
