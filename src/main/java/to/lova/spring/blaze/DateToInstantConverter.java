/*-
 * Copyright 2017-2019 Axians SAIV S.p.A.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
-*/
package to.lova.spring.blaze;

import java.lang.reflect.Type;
import java.time.Instant;
import java.util.Date;

import com.blazebit.persistence.view.spi.type.TypeConverter;

public class DateToInstantConverter implements TypeConverter<Date, Instant> {

    @Override
    public Class<?> getUnderlyingType(Class<?> owningClass, Type declaredType) {
        return Date.class;
    }

    @Override
    public Instant convertToViewType(Date object) {
        return object == null ? null : object.toInstant();
    }

    @Override
    public Date convertToUnderlyingType(Instant object) {
        return object == null ? null : Date.from(object);
    }

}
